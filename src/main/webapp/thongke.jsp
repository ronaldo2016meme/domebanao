<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thống kê doanh thu</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css?v=100">

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>

<body>
<%@ include file="menu.jsp" %>

<div class="thongke-container">

    <h2>THỐNG KÊ DOANH THU</h2>

    <form action="thongke" method="get" class="tk-filter">

        <label>Từ ngày</label>
        <input type="date" name="tuNgay" value="${param.tuNgay}">

        <label>Đến ngày</label>
        <input type="date" name="denNgay" value="${param.denNgay}">

        <button class="btn">Thống kê</button>

        <button type="button"
                    class="btn"
                    onclick="location.href='index.jsp'">
                Quay lại
            </button>

    </form>

    <div class="tk-card">

        <div class="tk-item">
            <h3>Tổng doanh thu</h3>
            <p>
                <fmt:formatNumber value="${tongDoanhThu}" pattern="#,##0"/> VNĐ
            </p>
        </div>

        <div class="tk-item">
            <h3>Tổng hóa đơn</h3>
            <p>${tongHoaDon}</p>
        </div>

        <div class="tk-item">
            <h3>Tổng sản phẩm bán</h3>
            <p>${tongSanPham}</p>
        </div>

    </div>

    <h3>Top 5 sản phẩm bán chạy</h3>

    <table class="tk-table">

        <tr>

            <th>Mã SP</th>

            <th>Tên sản phẩm</th>

            <th>Số lượng</th>

            <th>Doanh thu</th>

        </tr>

        <c:forEach items="${topSanPham}" var="sp">

            <tr>

                <td>${sp.maSP}</td>

                <td>${sp.tenSP}</td>

                <td>${sp.soLuongBan}</td>

                <td>
                    <fmt:formatNumber value="${sp.doanhThu}" pattern="#,##0"/> VNĐ
                </td>

            </tr>

        </c:forEach>

    </table>

    <br>

    <div class="chart-box">
        <canvas id="myChart"></canvas>
    </div>

</div>

<script>

const labels=[
<c:forEach items="${doanhThuNgay}" var="d">
'${d.ngayLap}',
</c:forEach>
];

const doanhThu=[
<c:forEach items="${doanhThuNgay}" var="d">
${d.doanhThu},
</c:forEach>
];

new Chart(document.getElementById("myChart"),{

    type:'bar',

    data:{
        labels:labels,
        datasets:[{
            label:'Doanh thu',
            data:doanhThu,
            backgroundColor:"#5DADE2",
            borderColor:"#2874A6",
            borderWidth:1
        }]
    },

    options:{
        responsive:true,
        maintainAspectRatio:false,

        scales:{
            y:{
                beginAtZero:true
            }
        }
    }

});
</script>

</body>
</html>