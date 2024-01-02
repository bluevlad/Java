/*
 * Copyright 2008-2009 MOPAS(MINISTRY OF SECURITY AND PUBLIC ADMINISTRATION).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.willbes.cmm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class HTMLTagFilter implements Filter{

    @SuppressWarnings("unused")
    private FilterConfig config;

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        if(!isMultipart(request)) {
            chain.doFilter(new HTMLTagFilterRequestWrapper((HttpServletRequest)request), response);
        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    public void destroy() {

    }

    /**
     * 클라이언트의 요청의 ContentType을 분석하여 multipart/form-data 형식인지 알아내는 메소드<br>
     *  - SUB 서블릿 클래스에서 파라미터를 받을때 사용한다.<br>
     *  - MultipartRequest 클래스를 사용하여 받을지 ServletRequest로 부터 받을지 결정지어준다.
     *
     */
    public boolean isMultipart(ServletRequest request)
            throws ServletException, IOException {
        String str = request.getContentType();
        return str != null && str.toLowerCase().startsWith("multipart/form-data");
    }

}
