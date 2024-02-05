package com.eat.eat_server.Authentication.Jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtAuthenticationProvider;

    public JwtAuthenticationFilter(JwtTokenProvider provider){
        this.jwtAuthenticationProvider = provider;
    }

    /**
     * 요청이 들어올 때마다 실행되는 메서드로,
     * 요청의 헤더에서 JWT 토큰을 추출하여 유효성을 검사하고,
     * 유효한 토큰이면 인증 정보를 추출하여 현재 보안 컨텍스트에 설정
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtAuthenticationProvider.resolveAccessToken(request); //request 헤더에서 토큰 가져옴

        if (token != null && jwtAuthenticationProvider.validAccessToken(token)){
            //유효한 토큰이면 jwtTokenProvider를 통해 Authentication 객체를 생성
            Authentication authentication = jwtAuthenticationProvider.getAuthentication(token);

            //현재 스레드의 security context에 인증 정보를 저장 -> 해당 요청을 처리하는 동안 인증된 사용자로서 권한이 부여
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);

    }
//    private final AuthenticationManager authenticationManager;
//    private final JwtProvider jwtProvider;
//
//    // 인증 객체(Authentication)을 만들기 시도
//    // attemptAuthentication 추상메소드의 구현은 상속한 UsernamePasswordAuthenticationFilter에 구현 되어 있습니다.
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try {
//
//            ObjectMapper om = new ObjectMapper();
//            User user = om.readValue(request.getInputStream(), User.class);
//
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                    user.getNickname(),
//                    user.getPassword()
//            );
//
//            /*
//            - Filter를 통해 AuthenticationToken을 AuthenticationManager에 위임한다.
//               UsernamePasswordAuthenticationToken 오브젝트가 생성된 후, AuthenticationManager의 인증 메소드를 호출한다.
//            - PrincipalDetailsService의 loadUserByUsername() 함수가 실행된다.
//            => 정상이면 authentication이 반환된다.
//            * */
//            Authentication authentication = authenticationManager.authenticate(authenticationToken);
//
//            /*
//            - authentication 객체가 session 영역에 정보를 저장한다. -> 로그인 처리
//            - authentication 객체가 세션에 저장한 '방식'을 반환한다.
//            - 참고 : security가 권한을 관리해주기 때문에 굳이 JWT에서는 세션을 만들필요는 없지만, 권한 처리를 위해 session을 사용한다.
//             */
//
//            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//            System.out.println("Authentication 확인: "+principalDetails.getUser().getNickname());
//
//            return  authentication;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    // attemptAuthentication 메소드가 호출 된 후 동작
//    // response에 JWT 토큰을 담아서 보내준다.
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//
//        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
//
//        Long id = principalDetails.getUser().getId();
//        String email = principalDetails.getUser().getEmail();
//        String userName = principalDetails.getUser().getNickname();
//
//        String jwtToken = jwtProvider.generateJwtToken(id, email, userName);
//
//        response.addHeader("Authorization", "Bearer " + jwtToken);
//
//    }

}
