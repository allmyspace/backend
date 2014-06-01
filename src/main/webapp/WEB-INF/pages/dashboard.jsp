<%@ page import="in.allmyspce.app.FileAPIController" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%--
  @author : jknair
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Dashboard</title>
    <%@include file="scripts.jsp"%>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
</head>

<body>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">AllMySpace</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/dashboard">Dashboard</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Services<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="/dropbox/token">DropBox</a></li>
                        <li><a href="/box/login">Box.com</a></li>
                    </ul>
                </li>
                <li><a href="/auth/logout">Logout</a></li>
            </ul>

        </div>
    </div>
</div>

<div class="container container-fluid ">
    <div class="well well-large">
        <div class="row-fluid">


        <div>
            <h1 class="page-header">Dashboard</h1>
            <div class="row-fluid">
                <div class="col-md-3 col-md-offset-3" style="background-color: dimgray;width:81px">
                    <div class="" style="color: #F38630;width:50px"> DropBox</div>
                    <div class="" style="color: #E0E4CC"> Box.com</div>
                </div>
                <div class="col-xs-6">
                    <canvas id="mySpaceChart" width="300px" height="200px" class="col-md-1 col-md-offset-4"></canvas>
                </div>

            </div>
            <br/>

            <div>
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th><h1>MySpace ...</h1></th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        String dropBox = "/resources/img/dropbox-icon.png";
                        String box = "/resources/img/box-icon.png";
                        int boxFreq = 0;
                        int dropBoxFreq = 0;
                        List<HashMap<String, Object>> myMap = (List<HashMap<String, Object>>) request.getAttribute("fileList");
                        for(HashMap<String, Object> result : myMap){
                            String path =  result.get("ty").equals("box") ? box : dropBox;
                            int t = result.get("ty").equals("box") ? boxFreq++ : dropBoxFreq++;
                            String shareApi = result.get("ty").equals("box") ? "/share/box/"+result.get("rid") : "/share/dropbox/"+result.get("rid");



                    %>
                    <tr>
                        <td>
                            <img src="<%out.print(path);%>" style="width: 50px; height: 50px" />
                        </td>
                        <td>
                            <span class="span8"><strong><%out.print(result.get("lid"));%></strong></span>
                        </td>
                        <%--<td>
                            <button class="btn btn-primary shareModal" data-toggle="modal" href="<%out.print(shareApi);%>" data-target="#myModal">Share</button>
                        </td>--%>
                    </tr>
                    <%
                        }
                    %>
<%--
                    <%
                        String rowStr = "<tr>\n" +
                                "<td>%s</td>\n" +
                                "<td>%s</td>\n" +
                                "<td>%s</td>\n" +
                                "<td>%s</td>\n" +
                                "<td>%s</td>\n" +
                                "</tr>";



                        for(HashMap<String, Object> result : myMap){
                            out.print(String.format(rowStr,result.get("rid"),result.get("lid"),result.get("mt"),result.get("sl"),result.get("ty")));
                        }

                    %>--%>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</div>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Share url</h4>

            </div>
            <div class="modal-body"><div class="te"></div></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

</body>

<script type="application/javascript" src="/resources/js/Chart.min.js"></script>
<script type="application/javascript">


    $(document).ready(function(){

        $(".shareModal").click(function(e){
            e.preventDefault();

            $.ajax({
                url : $(this).attr("href"),

            }).done(function(data){
                console.log(data);
            })

        })

        var data = [
            {
                value: <% out.print(dropBoxFreq);%>,
                color:"#F38630" ,
                label: "DropBox"
            },
            {
                value : <% out.print(boxFreq);%>,
                color : "#E0E4CC"
            }
        ] ;
        var ctx = $("#mySpaceChart").get(0).getContext("2d");
        console.log(ctx);
        var myNewChart = new Chart(ctx).Pie(data);
    });
</script>
</html>
