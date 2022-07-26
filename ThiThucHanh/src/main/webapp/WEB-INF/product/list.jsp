<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>[DH] Student List</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description">
    <meta content="Coderthemes" name="author">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <jsp:include page="/WEB-INF/layout/meta_css.jsp"></jsp:include>
<body class="">

<!-- Begin page -->
<div id="wrapper">
    <jsp:include page="/WEB-INF/layout/navbar-custom.jsp"></jsp:include>
    <!-- ========== Left Sidebar Start ========== -->
    <jsp:include page="/WEB-INF/layout/left-side-menu.jsp"></jsp:include>
    <!-- Left Sidebar End -->

    <!-- ============================================================== -->
    <!-- Start Page Content here -->
    <!-- ============================================================== -->

    <div class="content-page">
        <div class="content">

            <!-- Start Content-->
            <div class="container-fluid">

                <!-- start page title -->
                <div class="row">
                    <div class="col-12">
                        <div class="page-title-box">
                            <div class="page-title-right">
                                <ol class="breadcrumb m-0">
                                    <li class="breadcrumb-item"><a href="/students">Dong Hoi High School</a></li>
                                    <li class="breadcrumb-item active">Product List</li>
                                </ol>
                            </div>
                            <h4 class="page-title">Product List</h4>
                            <div>
                                <div class="col-4" style="float: left">
                                    <div id="button">
                                        <button type="button" class="btn btn-primary waves-effect waves-light"><a
                                                href="/products?action=add"><b style="color: white">Add Product</b></a>
                                        </button>
                                    </div>
                                </div>
                                <div class="col-5" style="float: left">
                                    <form method="get" action="/products?action=search">
                                        <input type="hidden" value="search" name="action">
                                        <div class="input-group">
                                            <input type="search" class="form-control" name="search"
                                                   placeholder="">
                                            <button class="btn btn-primary" type="submit">
                                                <i class="fe-search"></i>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end row -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="table-responsive mt-3">
                                    <table class="table table-hover table-centered mb-0">
                                        <thead>
                                        <tr>
                                            <th>id</th>
                                            <th>Name</th>
                                            <th>Price</th>
                                            <th>Quantity</th>
                                            <th>Color</th>
                                            <th>Describe</th>
                                            <th>Category</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="product" items="${requestScope.listProduct}">
                                            <tr>
                                                <th scope="row"><c:out value="${product.getId()}"/></th>
                                                <td>
                                                    <c:out value="${product.getName()}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${product.getPrice()}"/>
                                                </td>

                                                <td>
                                                    <c:out value="${product.getQuantity()}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${product.getColor()}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${product.getDescribe()}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${product.getCategory()}"/>
                                                </td>
                                                <td>
                                                    <div class="btn-group dropdown">
                                                        <a href="javascript: void(0);"
                                                           class="dropdown-toggle arrow-none btn btn-light btn-sm"
                                                           data-toggle="dropdown" aria-expanded="false"><i
                                                                class="mdi mdi-dots-horizontal"></i></a>
                                                        <div class="dropdown-menu dropdown-menu-right"
                                                             x-placement="bottom-end"
                                                             style="position: absolute; will-change: transform; top: 0; left: 0; transform: translate3d(-120px, 29px, 0px);">
                                                            <a class="dropdown-item"
                                                               href="/products?action=edit&id=${product.getId()}"><i
                                                                    class="mdi mdi-pencil mr-1 text-muted"></i>Edit</a>
                                                            <a class="dropdown-item"
                                                               href="/products?action=delete&id=${product.getId()}"><i
                                                                    class="mdi mdi-delete mr-1 text-muted"></i>Remove</a>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end row -->

            </div> <!-- container-fluid -->

        </div> <!-- content -->


        <!-- Footer Start -->
        <jsp:include page="/WEB-INF/layout/footer.jsp"></jsp:include>
        <%-- end Footer --%>
    </div>
    <%-- END wrapper --%>
    <jsp:include page="/WEB-INF/layout/script.jsp"></jsp:include>
</body>
</html>
<script>
    // $(document).ready(function () {
    //     $("#myInput").on("keyup", function () {
    //         var value = $(this).val().toLowerCase();
    //         $("#myTable tr").filter(function () {
    //             $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    //         });
    //     });
    // });
    $(document).ready(function () {
        $('select').niceSelect();
    });
</script>