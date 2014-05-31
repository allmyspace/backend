create table users(
username varchar(32),
password varchar(32),
dropboxtoken varchar(124),
boxtoken varchar(124),
created_at int(11),
dropboxtoken_created_at int(11),
boxtoken_createdat int(11)
);

create table files(
    local_path varchar(32),
    username varchar(32),
    service varchar(32),
    remote_id varchar(32),
    modified_at int(11),
    shared_link varchar(128)
);