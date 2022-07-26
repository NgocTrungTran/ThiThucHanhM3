<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Add Student</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description">
  <meta content="Coderthemes" name="author">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- App favicon -->
  <jsp:include page="/WEB-INF/layout/meta_css.jsp"></jsp:include>
</head>

<body>

<!-- Begin page -->
<div id="wrapper">

  <!-- Topbar Start -->
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
        <div class="row">
          <div class="col-12">
            <div class="page-title-box">
              <div class="page-title-right">
                <ol class="breadcrumb m-0">
                  <li class="breadcrumb-item"><a href="/students">Dong Hoi High School</a></li>
                  <li class="breadcrumb-item active"><a href="/products">Product List</a>
                  </li>
                  <li class="breadcrumb-item active">Add Product</li>
                </ol>
              </div>
            </div>
          </div>

        </div>
        <div class="row"
             style="align-items: center; justify-content: center; margin-top: 20px; margin-left: 100px">
          <div class="col-lg-8">
            <div class="card">
              <div class="card-body">
                <h4 class="header-title mb-3" style="font-size: larger">New Product</h4>

                <form method="post" class="needs-validation" novalidate=""
                      enctype="multipart/form-data">
                  <div class="form-group mb-3">
                    <label for="validationCustom01">Name</label>
                    <input type="text" class="form-control" id="validationCustom01" name="name"
                           placeholder="Name product" value="${name}" required="">
                    <div class="valid-feedback">
                      Looks good!
                    </div>
                    <div class="invalid-feedback">
                      Please enter product name!
                    </div>
                  </div>
                  <div class="form-group mb-3">
                    <label for="validationCustom02">Price</label>
                    <input type="text" class="form-control" id="validationCustom02" name="price"
                           placeholder="Price" value="${price}" required="">
                    <div class="valid-feedback">
                      Looks good!
                    </div>
                    <div class="invalid-feedback">
                      Please enter price!
                    </div>
                  </div>
                  <div class="form-group mb-3">
                    <label for="validationCustom03">Quantity</label>
                    <input type="text" class="form-control" name="quantity" placeholder="Quantity"
                           id="validationCustom03" value="${quantity}" required="">
                    <div class="valid-feedback">
                      Looks good!
                    </div>
                    <div class="invalid-feedback">
                      Please enter price!
                    </div>
                  </div>
                  <div class="form-group mb-3">
                    <label for="validationCustom04">Color</label>
                    <input type="text" class="form-control" name="color" id="validationCustom04"
                           placeholder="Color" value="${color}" required="">
                    <div class="invalid-feedback">
                      Please enter color!
                    </div>
                  </div>
                  <div class="form-group mb-3">
                    <label for="validationCustom05">Describe</label>
                    <input type="text" class="form-control" name="descri"
                           id="validationCustom05" value="${descri}"
                           placeholder="Describe" required="">
                    <div class="invalid-feedback">
                      Please Enter Describe!
                    </div>
                  </div>
                  <div class="form-group mb-3">
                    <label for="validationCustom06">Category</label>
                    <input type="text" class="form-control" name="category"
                           id="validationCustom06" value="${category}"
                           placeholder="Category" required="">
                    <div class="invalid-feedback">
                      Please Enter categorye!
                    </div>
                  </div>
                  <button class="btn btn-primary" type="submit" id="submit_add"><b
                          style="color: white;">Edit</b></button>
                  <button class="btn btn-warning" type="reset"><b style="color: white;">Reset</b>
                  </button>
                  <button type="button" class="btn btn-secondary waves-effect waves-light"><a
                          href="/products"><b style="color: white;">Back</b></a>
                  </button>
                </form>
                <div id="alertFormat">
                  ${errors}
                </div>
              </div> <!-- end card-body-->
            </div> <!-- end card-->
          </div> <!-- end col-->
        </div>
      </div>
    </div>
  </div>
</div>

<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->

<jsp:include page="/WEB-INF/layout/footer.jsp"></jsp:include>

</div>
<!-- END wrapper -->
<!-- Vendor js -->
<jsp:include page="/WEB-INF/layout/script.jsp"></jsp:include>
<script>
  validationCustom06.onchange = evt => {
    const [file] = validationCustom06.files
    if (file) {
      blah.src = URL.createObjectURL(file)
    }
  }
</script>
<%--                        script an hien--%>
</body>
</html>
<script>

</script>