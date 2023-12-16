<%@ page import="nhom9.phoneshop.model.bean.ProductBean"%>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh sửa sản phẩm</title>
    <style>
        /* Your existing styles here */ 
        #editProductForm {
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
    <% ProductBean product = (ProductBean) request.getAttribute("pd"); %>
    <h2>Chỉnh sửa sản phẩm</h2>
    <form name="editProductForm" id="editProductForm" action="EditProduct" method="post">
        <table id="registrationTable">
            <tr>
                <td>Tên sản phẩm</td>
                <td><label for="txtProductName"></label><input type="text" name="txtProductName" id="txtProductName" value="<%=product.getProductName()%>>" required>
                </td>
            </tr>
            <tr>
                <td>Giá sản phẩm</td>
                <td><label for="txtPrice"></label><input type="text" name="txtPrice" id="txtPrice" value="<%=product.getPrice()%>" required>
                </td>
            </tr>
            <tr>
                <td>Tên hãng</td>
                <td><label for="txtManufacturerName"></label><input type="text" name="txtManufacturerName" id="txtManufacturerName" value="<%=product.getManufacturerName()%>" required>
                </td>
            </tr>
            <tr>
                <td>CPU</td>
                <td><label for="txtCPU"></label><input type="text" name="txtCPU" id="txtCPU" value="<%=product.getCPU()%>" required>
                </td>
            </tr>
            <tr>
                <td>RAM</td>
                <td><label for="txtRAM"></label><input type="text" name="txtRAM" id="txtRAM" value="<%=product.getRAM()%>" required>
                </td>
            </tr>
            <tr>
                <td>Kích thước màn hình</td>
                <td><label for="txtDisplaySize"></label><input type="text" name="txtDisplaySize" id="txtDisplaySize" value="<%=product.getDisplaySize()%>" required>
                </td>
            </tr>
            <tr>
                <td>Chiều rộng màn hình</td>
                <td><label for="txtDisplayWidth"></label><input type="text" name="txtDisplayWidth" id="txtDisplayWidth" value="<%=product.getDisplayWidth()%>" required>
                </td>
            </tr>
            <tr>
                <td>Chiều cao màn hình</td>
                <td><label for="txtDisplayHeight"></label><input type="text" name="txtDisplayHeight" id="txtDisplayHeight" value="<%=product.getDisplayHeight()%>" required>
                </td>
            </tr>
            <tr>
                <%
                    ArrayList<String> os = new ArrayList<String>();
                    os.add("Android");
                    os.add("iOS");
                %>
                <td>Hệ điều hành</td>
                <td><label for="txtOS"></label>
                    <select id="txtOS" name="txtOS">
                        <% for(String name : os){ %>
                        <option value="<%=name%>" <%= (name.equals(product.getOS()))?"selected":"" %> ><%=name%></option>
                        <%} %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Dung lượng pin</td>
                <td><label for="txtBattery"></label><input type="text" name="txtBattery" id="txtBattery" value="<%=product.getBattery()%>" required>
                </td>
            </tr>
            <tr>
                <td>Dung lượng bộ nhớ</td>
                <td><label for="txtCapacity"></label><input type="text" name="txtCapacity" id="txtCapacity" value="<%=product.getCapacity()%>" required>
                </td>
            </tr>
            <tr>
                <td>Màu sắc</td>
                <td><label for="txtColor"></label><input type="file" name="txtColor" id="txtColor" value="<%=product.getColor()%>" required>
                </td>
            </tr>
            <tr>
                <td>Hình ảnh sản phẩm</td>
                <td><label for="txtImage"></label><input type="file" name="txtImage" id="txtImage" value="" required>
                </td>
            </tr>
            <tr>
                <td>Số lượng</td>
                <td><label for="txtQuantity"></label><input type="file" name="txtQuantity" id="txtQuantity" value="<%=product.getQuantity()%>" required>
                </td>
            </tr>
                <td></td>
                <td><input type="submit" name="Confirm" id="Confirm" value="Xác nhận"></td>
        </table>
    </form>
</body>

</html>
