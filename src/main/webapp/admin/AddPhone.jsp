<%@ page import="nhom9.phoneshop.model.bean.tables.Manufacturers"%>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm sản phẩm</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }
        h2 {
            color: #333;
        }
        form {
            width: 70%;
            max-width: 600px;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }
        td, th {
            padding: 10px;
            border: 1px solid #ddd;
        }
        label {
            display: none;
        }
        input[type="text"], select, input[type="file"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>

<body>
    <h2>Thêm sản phẩm</h2>
    <form name="addProductForm" id="addProductForm" action="<%=request.getContextPath()%>/authServlet?action=AddProduct" enctype="multipart/form-data" method="post">
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
                <td><label for="txtDisplaySize"></label><input type="text" name="txtDisplaySize" id="txtDisplaySize" value="" readonly required>
                </td>
            </tr>
            <tr>
                <td>Chiều rộng màn hình</td>
                <td><label for="txtDisplayWidth"></label><input type="text" name="txtDisplayWidth" id="txtDisplayWidth" value="" required oninput="updateDisplaySize()">
                </td>
            </tr>
            <tr>
                <td>Chiều cao màn hình</td>
                <td><label for="txtDisplayHeight"></label><input type="text" name="txtDisplayHeight" id="txtDisplayHeight" value="" required oninput="updateDisplaySize()">
                </td>
            </tr>
            <script>
                function updateDisplaySize() {
                    var width = document.getElementById("txtDisplayWidth").value;
                    var height = document.getElementById("txtDisplayHeight").value;
                    var displaySize = height + "x" + width;
                    document.getElementById("txtDisplaySize").value = displaySize;
                }
            </script>
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
