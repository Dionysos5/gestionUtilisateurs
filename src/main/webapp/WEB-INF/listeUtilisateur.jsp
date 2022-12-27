
<%@page import="beans.Utilisateur, java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
final String APP_ROOT = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=APP_ROOT%>/js/script.js"></script>
<script src="https://cdn.tailwindcss.com"></script>

</head>

<c:import url="inc/header.jsp"/>
<body>
	<div class="mx-auto max-w-screen-xl px-4 py-16 sm:px-6 lg:px-8">
  <div class="mx-auto max-w-content">
    <h1 class="text-center text-2xl font-bold text-indigo-600 sm:text-3xl mb-4">
      Liste des utilisateurs
    </h1>
	
		<%-- <%
		if (utilisateurs.isEmpty()) {
		%>
		<p>La liste d'utilisateurs est vide</p>
		<%
		} else {
		%> --%>

		<div class="overflow-x-auto border border-gray-200">
			<table class="min-w-full divide-y-2 divide-gray-200 text-sm">
				<thead class="bg-gray-100">
					<tr>
						<th
							class="whitespace-nowrap px-4 py-2 text-left font-bold text-gray-900">
							ID</th>
						<th
							class="whitespace-nowrap px-4 py-2 text-left font-bold text-gray-900">
							Prenom</th>
						<th
							class="whitespace-nowrap px-4 py-2 text-left font-bold text-gray-900">
							Nom</th>
						<th
							class="whitespace-nowrap px-4 py-2 text-left font-bold text-gray-900">
							Login</th>
						<th
							class="whitespace-nowrap px-4 py-2 text-left font-bold text-gray-900">
							Password</th>
						<th colspan="2"
							class="text-center items-center whitespace-nowrap px-4 py-2 text-left font-bold text-gray-900">
							Actions</th>
					</tr>
				</thead>

				<tbody class="divide-y divide-gray-200">
				<c:forEach items ="${ requestScope.utilisateurs}" var="utilisateur" >
         			<%-- Item <c:out value = "${i}"/><p> --%>
         			<tr>
						<td class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
							${utilisateur.id }</td>
						<td class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
							${utilisateur.prenom}</td>

						<td class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
							${utilisateur.nom}</td>
						<td class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
							${utilisateur.login}</td>
						<td class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
							${utilisateur.password}</td>
						<td class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
							<a
							class="rounded-lg bg-blue-500 px-2 py-1 text-sm font-medium text-white"
							href="<%=APP_ROOT%>/update?id=${utilisateur.id }">
							Modifier
							</a>
						</td>
						<td class="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
							<a
								class="rounded-lg bg-red-400 px-2 py-1 text-sm font-medium text-white"
								href="<%=APP_ROOT%>/delete?id=${utilisateur.id }"
								onclick="return confirmSuppression()">
								Supprimer
							</a>
						</td>
					</tr>
      			</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
	</div>
	
</body>
</html>