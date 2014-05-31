package in.allmyspce.app.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: aman.ag
 * Date: 5/31/14
 * Time: 7:28 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CustomUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
