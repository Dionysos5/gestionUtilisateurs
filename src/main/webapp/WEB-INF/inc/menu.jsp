<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% final String APP_ROOT = request.getContextPath(); %>
<div id="menu">
		<ul>
			<li><a href="<%=APP_ROOT%>">Accueil</a></li>
			<li><a href="<%=APP_ROOT%>/list">Lister</a></li>
			<li><a href="<%=APP_ROOT%>/add">Ajouter</a></li>
		</ul>
</div>
