<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Каталог товаров</title>

</head>
<body>
<jsp:include page="jspf/menu.jspf"/>
<br>
<br>

<h2>Добро пожаловать в <strong>каталог товаров</strong> интернет-магазина!</h2>

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <c:url value="/new" var="newEditUrl"/>
            <a class="btn btn-primary" href="${newEditUrl}">Add product</a>
        </div>

        <div class="col-12">
            <table class="table table-bordered my-2">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Price</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>

                    <tr>
                        <th scope="row"> 1 </th>

                        <td> MacBookPro 2015 </td>
                        <td> Apple professional laptop </td>
                        <td> 1000 </td>

                        <td>
                            <c:url value="/edit" var="productEditUrl">
                                <c:param name="id" value="${prod.id}"/>
                            </c:url>
                            <a class="btn btn-success" href="${productEditUrl}"><i class="fas fa-edit"></i></a>
                            <a class="btn btn-danger" href="#"><i class="far fa-trash-alt"></i></a>
                        </td>
                    </tr>

                <tr>
                    <th scope="row"> 2 </th>

                    <td> MacBookAir 2015 </td>
                    <td> Apple laptop for surfing </td>
                    <td> 700 </td>

                    <td>
                        <c:url value="/edit" var="productEditUrl">
                            <c:param name="id" value="${prod.id}"/>
                        </c:url>
                        <a class="btn btn-success" href="${productEditUrl}"><i class="fas fa-edit"></i></a>
                        <a class="btn btn-danger" href="#"><i class="far fa-trash-alt"></i></a>
                    </td>
                </tr>

                <tr>
                    <th scope="row"> 3 </th>

                    <td> Apple Ipad </td>
                    <td> Apple tablet </td>
                    <td> 900 </td>

                    <td>
                        <c:url value="/edit" var="productEditUrl">
                            <c:param name="id" value="${prod.id}"/>
                        </c:url>
                        <a class="btn btn-success" href="${productEditUrl}"><i class="fas fa-edit"></i></a>
                        <a class="btn btn-danger" href="#"><i class="far fa-trash-alt"></i></a>
                    </td>
                </tr>

                <tr>
                    <th scope="row"> 4 </th>

                    <td> товар №4 </td>
                    <td> Apple товар </td>
                    <td> 850 </td>

                    <td>
                        <c:url value="/edit" var="productEditUrl">
                            <c:param name="id" value="${prod.id}"/>
                        </c:url>
                        <a class="btn btn-success" href="${productEditUrl}"><i class="fas fa-edit"></i></a>
                        <a class="btn btn-danger" href="#"><i class="far fa-trash-alt"></i></a>
                    </td>
                </tr>

<%--                и так далее еще 5 товаров копипастом--%>

            </table>
        </div>
    </div>
</div>


</body>
</html>
