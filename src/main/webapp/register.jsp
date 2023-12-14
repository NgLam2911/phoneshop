<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration</title>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
</head>

<body>
    <h2>Đăng ký</h2>
    <form name="registerForm" id="registerForm" action="RegisterServlet" method="post">
        <table id="registrationTable">
            <tr>
                <td>Tên người dùng (Nhập họ và tên)</td>
                <td><label for="txtCustomerName"></label><input type="text" name="txtCustomerName" id="txtCustomerName" value="">
                </td>
            </tr>
            <tr>
                <td>Tên tài khoản</td>
                <td><label for="txtUsername"></label><input type="text" name="txtUsername" id="txtUsername" value=""></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><label for="txtEmail"></label><input type="email" name="txtEmail" id="txtEmail" value=""></td>
            </tr>
            <tr>
                <td>Số điện thoại</td>
                <td><label for="txtPhone"></label><input type="text" name="txtPhone" id="txtPhone" value=""></td>
            </tr>
            <tr>
                <td>Địa chỉ</td>
                <td><label for="txtAddress"></label><input type="text" name="txtAddress" id="txtAddress" value=""></td>
            </tr>
            <tr>
                <td>Mật khẩu</td>
                <td><label for="txtPassword"></label><input type="password" name="txtPassword" id="txtPassword" value=""></td>
            </tr>
            <tr>
                <td>Xác nhận mật khẩu</td>
                <td><label for="txtConfirmPassword"></label><input type="password" name="txtConfirmPassword" id="txtConfirmPassword" value=""></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" name="Register" id="Register" value="Đăng ký"></td>
            </tr>
        </table>
    </form>
</body>

</html>
