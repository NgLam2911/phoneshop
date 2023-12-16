<?php
session_start();
// error_reporting(0);
?>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="css/bootstrap.min.css" />
  <link rel="stylesheet" href="css/style.css" />
  <link rel="stylesheet" href="css/query.css">
  <link rel="stylesheet" href="css/singleproduct.css">
  <script src="js/bootstrap.min.js"></script>
  <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
  <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
  <link rel="stylesheet" href="css/icofont.min.css" />
  <link rel="stylesheet" href="css/cartstyle.css">
  <title>Trang giỏ hàng</title>
</head>

<body>
  <script defer src="js/script.js"></script>
  <?php
  include("config.php");
  include("modules/cart/headercart.php");
  ?>
  <main class="main-page">

    <section class="cart-header">
      <div class="container-cart">
        <div class="logo-title">
          <img src="admin/img/shopeelogo.png" alt="" srcset="">
          <span class="line-cut"></span>
          <h1 class="title">Giỏ Hàng</h1>
        </div>

        <div class="search-tool search-tool-cart">
          <input class="input-search input-search-cart" type="text" name="" value="" placeholder="Tìm sản phẩm trong giỏ hàng của bạn" />
          <button class="btn-search btn-search-cart">
            <ion-icon class="icon icon-search icon-search-cart" name="search-outline"></ion-icon>
          </button>
        </div>

      </div>
    </section>

    <?php
    $total = 0;
    $totalgiohang = 0;
    if (isset($_GET["idaccount"])) {
      $id_account = $_GET["idaccount"];
    }
    $error = false;
    $orderSuccess = false;
    if (isset($_POST["soluong"])) {
      $soluongsanpham = $_POST["soluong"];
    }

    if (!isset($_SESSION["cart"])) {
      $_SESSION["cart"] = array();
    }
    function updare_cart($add = false)
    //  Phân biệt giữa add giỏ hàng và cập nhật
    {
      error_reporting(0);
      foreach ($_POST["soluong"] as $id => $soluong) {
        if ($soluong == 0) {
          unset($_SESSION["cart"][$id]);
        } else {
          if ($add) {
            $_SESSION["cart"][$id] += $soluong;
          } else {
            $_SESSION["cart"][$id] = $soluong;
          }
        }
      }
    }
    if (isset($_GET["ac"])) {
      switch ($_GET["ac"]) {
        case "add":
          updare_cart(true);
          break;
        case "deleted":
          if (isset($_GET["idsanpham"])) {
            unset($_SESSION["cart"][$_GET["idsanpham"]]);
          }
          header("location: cart.php");
          break;
        case "update":
          if (isset($_POST["update-click"])) {
            if (empty($_SESSION["cart"])) {
              $error = "Giỏ hàng rỗng";
            } else {
              updare_cart();
              header("location: cart.php");
            }
          } else if (isset($_POST["order-click"])) {
            if (isset($_GET["idaccount"])) {
              $id_account = $_GET["idaccount"];
            }
            // var_dump($_POST);exit;
            if (empty($_POST["name"])) {
              $error = "Bạn chưa nhập tên người nhận";
            } else if (empty($_POST["phone"])) {
              $error = "Bạn chưa nhập số điện thoại người nhận";
            } else if (empty($_POST["address"])) {
              $error = "Bạn chưa nhập địa chỉ người nhận";
            } elseif (empty($_POST["soluong"])) {
              $error = "Giỏ hàng rỗng";
            }
            if ($error == false && !empty($_POST["soluong"])) {
              // "SELECT * FROM `sanpham` WHERE `idsanpham` IN (2,4)"
              $products2 = mysqli_query($connect, "SELECT * FROM `sanpham` WHERE `idsanpham` IN (" . implode(",", array_keys($_POST["soluong"])) . ")");
              $orderProducts = array();
              while ($row = mysqli_fetch_array($products2)) {
                $orderProducts[] = $row;
                $totalgiohang += $row['gia'] * $_POST["soluong"][$row['idsanpham']];
              }
              $insertOrder = mysqli_query($connect, "INSERT INTO `cart` (`idcart`, `hoten`, `sdt`, `diachi`, `tongtien`, `idaccount`, `ngaytaogiohang`, `ngaycapnhat`) VALUES (NULL, '" . $_POST["name"] . "', '" . $_POST["phone"] . "', '" . $_POST["address"] . "', '" . $totalgiohang . "', '" . $id_account . "', '" . time() . "', '" . time() . "');");
              // "INSERT INTO `cart` (`idcart`, `hoten`, `sdt`, `diachi`, `tongtien`, `idaccount`, `ngaytaogiohang`, `ngaycapnhat`) VALUES (NULL, 'nguyenthanhtri', '09558', '84tnansdja', '66060', '16', '2023-06-20', '2023-06-21');"
              $orderID = $connect->insert_id;
              $insertString = "";
              // $num_end = 0;
              foreach ($orderProducts as $key => $products2) {
                $insertString .= "(NULL, '" . $products2["idsanpham"] . "', '" . $orderID . "', '" . $_POST["soluong"][$products2['idsanpham']] . "', '" . $products2["gia"] . "', '" . time() . "', '" . time() . "')";
                if ($key != count($orderProducts) - 1) {
                  $insertString .= ", ";
                }
              }
              // var_dump($insertString);exit;
              $insertOrder = mysqli_query($connect, "INSERT INTO `orderdetail` (`iddetail`, `idproduct`, `idcart`, `soluong`, `price`, `ngaydat`, `ngaycapnhat`) VALUES " . $insertString . ";");
              $orderSuccess = "Đặt hàng thành công";
              unset($_SESSION["cart"]);
            }
          }
          break;
      }

      // var_dump($_SESSION["cart"]);
    }
    if (!empty($_SESSION["cart"])) {
      // Kiểm tra sản phẩm
      // var_dump(implode(", ", array_keys($_SESSION["cart"])));
      $allProdcutId = implode(", ", array_keys($_SESSION["cart"]));
      $products =  mysqli_query($connect, "SELECT * FROM sanpham WHERE idsanpham in ($allProdcutId)");
      // var_dump("SELECT * FROM sanpham WHERE idsanpham in ($allProdcutId)") Kiểm tra câu lệnh
      // var_dump($result);
    }

    $connect = mysqli_connect("localhost", "root", "", "shopeedata");
    mysqli_set_charset($connect, "UTF8");
    // $sql_cart = "SELECT * FROM sanpham, orderdetail WHERE idsanpham = idproduct and idcart = (SELECT idcart FROM cart, account WHERE id=idaccount AND idaccount ='$id_account')";
    // $load_cart = mysqli_query($connect, $sql_cart);
    ?>

    <section class="cart">
      <?php if (!empty($error)) { ?>
        <div id="notify-msg">
          <?= $error ?> <a href="javascript:history.back()">Quay lại</a>
        </div>
      <?php } elseif (!empty($orderSuccess)) { ?>
        <div id="notify-msg">
          <?= $orderSuccess ?> <a href="index.php">Tiếp tục mua hàng</a>
        </div>
      <?php } else { ?>
        <div class="container-cart cart-order">
          <h2 class="home-page-title"><a href="index.php">Trang Chủ</a></h2>
          <div class="cart-detail">
            <form action="cart.php?ac=update&idaccount=<?php echo $_SESSION['id']; ?>" method="post">
              <table>
                <tr>
                  <th class="stt-cart">STT</th>
                  <th class="product-title-cart">Tên sản phẩm</th>
                  <th class="picture-image-cart">Ảnh sản phẩm</th>
                  <th class="product-price-cart">Đơn giá</th>
                  <th class="volume-cart">Số lượng</th>
                  <th class="purchase-cart">Thành tiền</th>
                  <th class="remove-btn-cart">Xóa</th>
                </tr>
                <?php
                if (!empty($products)) {
                  $stt = 1;
                  while ($row = mysqli_fetch_array($products)) {
                    // var_dump($row);
                ?>
                    <tr>
                      <td><?php echo $stt; ?></td>
                      <td class="tensanpham" style="text-align: left;
                          font-size: 1.8rem;
                          padding-left: 1rem"><?php echo ($row['tensanpham']); ?></td>
                      <td><img class="image-cart-product" src="admin/modules/sanpham/upload/<?php echo $row['hinhanhsanpham'] ?>" alt="" srcset=""></td>
                      <td><?php echo number_format(($row['gia']), 0, ","); ?></td>
                      <td><input type="text" name="soluong[<?= $row["idsanpham"] ?>]" value="<?= $_SESSION["cart"][$row['idsanpham']]; ?>" onkeypress="return isNumberKey(event)" maxlength="2"></td>
                      <td><?php echo number_format((($row['gia']) * $_SESSION["cart"][$row['idsanpham']]), 0, ",");  ?></td>
                      <td><a href="cart.php?ac=deleted&idsanpham=<?= $row['idsanpham']; ?>">Xóa</a></td>
                    </tr>
                  <?php
                    $total += ($row['gia']) * $_SESSION["cart"][$row['idsanpham']];;
                    $stt++;
                  }
                } else { ?>
                  <td></td>
                  <td class="tensanpham" style="text-align: left;
                          font-size: 1.8rem;
                          padding-left: 1rem"></td>
                  <td><img class="image-cart-product" src="" alt="" srcset=""></td>
                  <td></td>
                  <td><input type="text" name="" value="" onkeypress="return isNumberKey(event)" maxlength="2"></td>
                  <td></td>
                  <td><a href="">Xóa</a></td>
                <?php
                }
                ?>
                <tr>
                  <td></td>
                  <td>Tổng số tiền</td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td><?php echo number_format($total, 0, ",");  ?></td>
                  <td></td>
                </tr>
              </table>

              <input class="sumbit-btn-cart" type="submit" name="update-click" value="Cập nhật">

              <div class="cut-line"></div>
              <div class="orderCart-detail">
                <div><label for="">Người nhận</label><input type="text" value="" name="name"></div>
                <div><label for="">Điện thoại</label><input type="text" value="" onkeypress="return isNumberKey(event)" name="phone" maxlength="10"></div>
                <div><label for="">Địa chỉ</label><input type="text" value="" name="address"></div>
                <div><label for="">Ghi chú</label><textarea type="text" value="" name="note"></textarea></div>
              </div>
              <input class="sumbit-btn-cart btn-order" type="submit" value="Đặt hàng" name="order-click">
            </form>
          </div>
        </div>
      <?php } ?>
    </section>
  </main>
</body>

</html>
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