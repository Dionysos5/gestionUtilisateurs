<%@page import="forms.LoginUserForm"%>

<%
final String APP_ROOT = request.getContextPath();
LoginUserForm form = (LoginUserForm) request.getAttribute("form");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="<%=APP_ROOT%>/js/script.js"></script>
<script src="https://cdn.tailwindcss.com"></script>
<title>Gestion des utilisateurs</title>
</head>
<body>
	<%@ include file="inc/header.jsp"%>
	<div id="corps">

<div class="mx-auto max-w-screen-xl px-4 py-16 sm:px-6 lg:px-8">
  <div class="mx-auto max-w-lg">
    <h1 class="text-center text-2xl font-bold text-indigo-600 sm:text-3xl">
      Se connecter
    </h1>

    <form method="post" action="" class="mt-6 mb-0 space-y-4 rounded-lg p-8 shadow-2xl">
<!-- TODO: Investigate why conditional class rending with EL is not working on span -->
      <span
  class="flex mx-auto items-center justify-center rounded-full bg-red-100 text-red-700"
>
 
  <p class="whitespace-nowrap text-sm">${form.statusMessage}</p>
</span>
     
       <div>
        <label for="password" class="text-sm font-medium">Login</label>

        <div class="relative mt-1">
          <input
            type="text"
            id="login"
            name="login"
            class="w-full rounded-lg border-gray-200 p-4 pr-12 text-sm shadow-sm"
            placeholder="Saisir login"
            value="${form.utilisateur.login }"
          />

          <span class=" text-red-500 absolute inset-y-0 right-4 inline-flex items-center">
            ${form.erreurs.login}
          </span>
        </div>
      </div>
      <div>
        <label for="password" class="text-sm font-medium">Mot de passe</label>

        <div class="relative mt-1">
          <input
            type="password"
            id="password"
            name="password"
            class="w-full rounded-lg border-gray-200 p-4 pr-12 text-sm shadow-sm"
            placeholder="Saisir mot de passe"
            value="${form.utilisateur.password }"
          />

          <span class=" text-red-500 absolute inset-y-0 right-4 inline-flex items-center">
            ${form.erreurs.password}
          </span>
        </div>
      </div>
     
      <button
        type="submit"
        class="block w-full rounded-lg bg-indigo-600 px-5 py-3 text-sm font-medium text-white"
      >
        Connexion
      </button>

    </form>
  </div>
</div>
	
	</div>

</body>
</html>