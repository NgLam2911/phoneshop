<!DOCTYPE html>
<html>
<head>
    <title>Cart</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        h1 {
            color: #333;
            text-align: center;
            margin-top: 50px;
        }

        table {
            width: 80%;
            margin: 50px auto;
            border-collapse: collapse;
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:hover {background-color: #f5f5f5;}
    </style>
</head>
<body>
    <h1>Cart</h1>
    
    <table>
        <thead>
            <tr>
                <th>Item</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
        </thead>
        <tbody>
            <% 
            ArrayList<CartItem> cartItems = (ArrayList<CartItem>) session.getAttribute("cartItems");
            if (cartItems != null) {
                for(CartItem item : cartItems) {
            %>
                <tr>
                    <td><%= item.getName() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td><%= item.getPrice() %></td>
                </tr>
            <% 
                }
            } 
            %>
        </tbody>
    </table>
</body>
</html>