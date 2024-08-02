package bless.leandro.Vendas.service.imp;

import bless.leandro.Vendas.domain.entity.Usuario;
import bless.leandro.Vendas.domain.repository.Usuarios;
import bless.leandro.Vendas.exception.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImp implements UserDetailsService {

    @Autowired
    private Usuarios usuarioRepository;

   // @Autowired
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public UserDetails autenticar(Usuario usuario){
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senha = encoder.matches(usuario.getSenha(),user.getPassword());

        if(senha){
            return user;
        }throw  new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario =  usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

        String [] roles = usuario.isAdmin() ? new String[]{"ADMIN","USER"} : new String[] {"USER"};

        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }
}