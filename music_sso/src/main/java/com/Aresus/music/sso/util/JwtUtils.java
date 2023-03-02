package com.Aresus.music.sso.util;

import com.Aresus.music.pojo.User;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.codec.binary.Base64;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    private static final String salt = "wang_jia_le_ke_ai_nie_wo_da_ge_jue_jue_zi_nai_bao_ni_dai_wo_zou_ba";
    private static final Key key = Keys.hmacShaKeyFor(Base64.decodeBase64(salt));

    public static String createToken(Object user){
        Map<String, Object> claim = new HashMap<>();
        claim.put("user", user);
        return Jwts.builder().signWith(key).setClaims(claim).setIssuedAt(new Date()).compact();
    }

    public static Object checkToken(String token){
        Claims claims = (Claims)Jwts.parserBuilder().setSigningKey(key).build().parse(token).getBody();
        String str = claims.get("user").toString();
        return JSON.parseObject(str, User.class);
    }

}
