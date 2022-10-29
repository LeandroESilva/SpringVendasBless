package bless.leandro.Vendas.security.jwt;

import bless.leandro.Vendas.VendasApplication;
import bless.leandro.Vendas.domain.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    //Personalizando o token com mais informações do que as informações padrão da JWT
    private HashMap<String, Object> personalizarTokenJwt(){
        HashMap<String, Object> claims = new HashMap<>();
        claims.   put("Email", "usuario@gmail.com");
        claims.put("Roles","admin");
        return claims;
    }

    public String gerarToken(Usuario usuario){

        long expString = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);
        return Jwts.builder()
                .setSubject(usuario.getLogin())
                .setExpiration(data)
                //.setClaims(personalizarTokenJwt())
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();
    }

    //Decodificar token
    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

    //Valida se o token não expirou
    public boolean tokenValido(String token){
        try {
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data =  dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return LocalDateTime.now().isBefore(data);
        }catch (Exception e){
            return false;
        }
    }

    public String obterLoginUsuario(String token) throws  ExpiredJwtException{
        return (String) obterClaims(token).getSubject();
    }

    //Teste do token
    public static void main(String[] args){
        ConfigurableApplicationContext contexto = SpringApplication.run(VendasApplication.class);
        JwtService service = contexto.getBean(JwtService.class);
        Usuario usuario = Usuario.builder().login("leandro").build();
        String token = service.gerarToken(usuario);

        System.out.println("Token: "+ token);
        System.out.println("Validar token: " + service.tokenValido(token));
        System.out.println("Usuario do token:" + service.obterLoginUsuario(token));

    }

}
