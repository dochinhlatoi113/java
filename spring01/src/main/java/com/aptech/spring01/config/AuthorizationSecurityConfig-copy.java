// package com.aptech.spring01.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.builders.WebSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
// import
// org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
// import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

// @Configuration
// @EnableWebSecurity
// public class AuthorizationSecurityConfig {
// @Autowired
// private UserDetailsService userDetailsService;

// @Autowired
// private HandleLoginSuccess successHandler;

// @Bean
// public static PasswordEncoder passwordEncoder() {
// return new BCryptPasswordEncoder();
// }

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http,
// HandlerMappingIntrospector introspector)
// throws Exception {
// MvcRequestMatcher.Builder mvcMatcherBuilder = new
// MvcRequestMatcher.Builder(introspector);
// http.authorizeHttpRequests((authorizeHttp) -> authorizeHttp
// .requestMatchers(mvcMatcherBuilder.pattern("/login")).permitAll()
// .requestMatchers(mvcMatcherBuilder.pattern("/register/**")).permitAll()
// .requestMatchers(mvcMatcherBuilder.pattern("/uploads")).permitAll()
// .requestMatchers(mvcMatcherBuilder.pattern("/uploads/**")).permitAll()
// .requestMatchers(new AntPathRequestMatcher("/assets/**")).permitAll()
// .requestMatchers(mvcMatcherBuilder.pattern("/customer/**")).permitAll()
// .requestMatchers(new
// AntPathRequestMatcher("/admin/**")).hasAnyAuthority("Admin")
// .anyRequest().authenticated())
// .formLogin(
// form -> form
// .loginPage("/login")
// .loginProcessingUrl("/login")
// .successHandler(successHandler)
// .permitAll())
// .logout(
// logout -> logout
// .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
// .permitAll());
// // .csrf((csrf) -> csrf
// // .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
// // .csrfTokenRequestHandler(requestHandler)
// // );

// return http.build();
// }

// @Autowired
// public void configureGlobal(AuthenticationManagerBuilder auth) throws
// Exception {
// auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
// }
// }
