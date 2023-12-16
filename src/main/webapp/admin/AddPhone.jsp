<%@ page import="nhom9.phoneshop.model.bean.tables.Manufacturers"%>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm sản phẩm</title>
    <style>
        /* Your existing styles here */ 
        #addProductForm {
            margin-top: 20px;
        }
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
            background-color: #f4f4f4;
        }

        h1 {
            color: #333;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }

        th,
        td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        a {
            text-decoration: none;
            color: #3498db;
        }

        a:hover {
            color: #207db5;
        }

        form {
            margin-top: 20px;
        }
    </style>
</head>

<body>
    <h2>Thêm sản phẩm</h2>
    <form name="addProductForm" id="addProductForm" action="<%=request.getContextPath()%>/authServlet?action=AddProduct" method="post">
        <table id="registrationTable">
            <tr>
                <td>Tên sản phẩm</td>
                <td><label for="txtProductName"></label><input type="text" name="txtProductName" id="txtProductName" value="" required>
                </td>
            </tr>
            <tr>
                <td>Giá sản phẩm (VNĐ)</td>
                <td><label for="txtPrice"></label><input type="text" name="txtPrice" id="txtPrice" value="" required>
                </td>
            </tr>
            <tr>
                <td>Tên hãng</td>
                <td><label for="txtManufacturerName"></label><select name="txtManufacturerName" id="txtManufacturerName">
					<% ArrayList<Manufacturers> mfList = (ArrayList<Manufacturers>)request.getAttribute("mfList"); 
						for (int i = 0; i < mfList.size(); i++) {
					%>
						<option value=<%= mfList.get(i).getManufacturerName() %>><%= mfList.get(i).getManufacturerName() %></option>
					<% } %>
					</select>
                </td>
            </tr>
            <tr>
                <td>CPU</td>
                <td><label for="txtCPU"></label><input type="text" name="txtCPU" id="txtCPU" value="" required>
                </td>
            </tr>
            <tr>
                <td>RAM (GB)</td>
                <td><label for="txtRAM"></label><input type="text" name="txtRAM" id="txtRAM" value="" required>
                </td>
            </tr>
            <tr>
                <td>Kích thước màn hình</td>
                <td><label for="txtDisplaySize"></label><input type="text" name="txtDisplaySize" id="txtDisplaySize" value="" required>
                </td>
            </tr>
            <tr>
                <td>Chiều rộng màn hình</td>
                <td><label for="txtDisplayWidth"></label><input type="text" name="txtDisplayWidth" id="txtDisplayWidth" value="" required>
                </td>
            </tr>
            <tr>
                <td>Chiều cao màn hình</td>
                <td><label for="txtDisplayHeight"></label><input type="text" name="txtDisplayHeight" id="txtDisplayHeight" value="" required>
                </td>
            </tr>
            <tr>
                <td>Hệ điều hành</td>
                <td><label for="txtOS"></label>
                    <select id="txtOS" name="txtOS">
                        <option value="Android">Android</option>
                        <option value="iOS">iOS</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Dung lượng pin (mAh)</td>
                <td><label for="txtBattery"></label><input type="text" name="txtBattery" id="txtBattery" value="" required>
                </td>
            </tr>
            <tr>
                <td>Dung lượng bộ nhớ (GB)</td>
                <td><label for="txtCapacity"></label><input type="text" name="txtCapacity" id="txtCapacity" value="" required>
                </td>
            </tr>
            <tr>
                <td>Màu sắc</td>
                <td><label for="txtColor"></label><input type="text" name="txtColor" id="txtColor" value="" required>
                </td>
            </tr>
            <tr>
                <td>Hình ảnh sản phẩm</td>
                <td><label for="txtImage"></label><input type="file" name="txtImage" id="txtImage" value="" required>
                </td>
            </tr>
            <tr>
                <td>Số lượng</td>
                <td><label for="txtQuantity"></label><input type="text" name="txtQuantity" id="txtQuantity" value="" required>
                </td>
            </tr>
                <td></td>
                <td><input type="submit" name="Register" id="Register" value="Thêm mới"></td>
            </tr>
        </table>
    </form>
</body>

</html>
