package dao;

import model.NhanVien;
import service.ConnectService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDao {

    public List<NhanVien> getAll() {

        List<NhanVien> list = new ArrayList<>();

        String sql =
                "SELECT nv.*, r.TenRole, tk.MaTK " +
                        "FROM NHANVIEN nv " +
                        "LEFT JOIN Role r ON nv.MaRole = r.MaRole " +
                        "LEFT JOIN TaiKhoan tk ON nv.MaNV = tk.MaNV";

        try {

            Connection con = new ConnectService().myConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                NhanVien nv = new NhanVien();

                nv.setMaNV(rs.getInt("MaNV"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setSdt(rs.getString("SDT"));
                nv.setEmail(rs.getString("Email"));
                nv.setCccd(rs.getString("CCCD"));
                nv.setChucVu(rs.getString("TenRole"));
                nv.setMaTrangThai(rs.getString("MaTrangThai"));
                nv.setCoTaiKhoan(rs.getObject("MaTK") != null);

                list.add(nv);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public int insert(NhanVien nv){

        int maNV = 0;

        String sql = "INSERT INTO NHANVIEN(HoTen,NgaySinh,GioiTinh,QuocTich,QueQuan,NoiThuongTru,SDT,Email,CCCD,MaTrangThai,MaRole) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        try{

            Connection con = new ConnectService().myConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, nv.getHoTen());
            ps.setDate(2, nv.getNgaySinh());
            ps.setString(3, nv.getGioiTinh());
            ps.setString(4, nv.getQuocTich());
            ps.setString(5, nv.getQueQuan());
            ps.setString(6, nv.getNoiThuongTru());
            ps.setString(7, nv.getSdt());
            ps.setString(8, nv.getEmail());
            ps.setString(9, nv.getCccd());
            ps.setString(10, nv.getMaTrangThai());
            ps.setString(11, nv.getMaRole());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()){
                maNV = rs.getInt(1);
            }

            rs.close();
            ps.close();
            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return maNV;
    }

    public void delete(int id){

        String sql = "DELETE FROM NHANVIEN WHERE MaNV=?";

        try{
            Connection con = new ConnectService().myConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,id);

            ps.executeUpdate();

            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public NhanVien getById(int id){

        NhanVien nv = null;

        String sql = "SELECT * FROM NHANVIEN WHERE MaNV=?";

        try{
            Connection con = new ConnectService().myConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                nv = new NhanVien();

                nv.setMaNV(rs.getInt("MaNV"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setQuocTich(rs.getString("QuocTich"));
                nv.setQueQuan(rs.getString("QueQuan"));
                nv.setNoiThuongTru(rs.getString("NoiThuongTru"));
                nv.setSdt(rs.getString("SDT"));
                nv.setEmail(rs.getString("Email"));
                nv.setCccd(rs.getString("CCCD"));
                nv.setMaTrangThai(rs.getString("MaTrangThai"));
                nv.setMaRole(rs.getString("MaRole"));
            }

            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return nv;
    }
    public void update(NhanVien nv){

        String sql = "UPDATE NHANVIEN SET HoTen=?, NgaySinh=?, GioiTinh=?, QuocTich=?, QueQuan=?, NoiThuongTru=?, SDT=?, Email=?, CCCD=?, MaTrangThai=?, MaRole=? WHERE MaNV=?";

        try{

            Connection con = new ConnectService().myConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nv.getHoTen());
            ps.setDate(2, nv.getNgaySinh());
            ps.setString(3, nv.getGioiTinh());
            ps.setString(4, nv.getQuocTich());
            ps.setString(5, nv.getQueQuan());
            ps.setString(6, nv.getNoiThuongTru());
            ps.setString(7, nv.getSdt());
            ps.setString(8, nv.getEmail());
            ps.setString(9, nv.getCccd());
            ps.setString(10, nv.getMaTrangThai());
            ps.setString(11, nv.getMaRole());
            ps.setInt(12, nv.getMaNV());

            ps.executeUpdate();

            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public NhanVien findByMaNV(int maNV) {

        NhanVien nv = null;

        String sql =
                "SELECT nv.*, r.TenRole " +
                        "FROM NHANVIEN nv " +
                        "LEFT JOIN Role r ON nv.MaRole = r.MaRole " +
                        "WHERE nv.MaNV=?";

        try {
            Connection con = new ConnectService().myConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, maNV);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                nv = new NhanVien();

                nv.setMaNV(rs.getInt("MaNV"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setQuocTich(rs.getString("QuocTich"));
                nv.setQueQuan(rs.getString("QueQuan"));
                nv.setNoiThuongTru(rs.getString("NoiThuongTru"));
                nv.setSdt(rs.getString("SDT"));
                nv.setEmail(rs.getString("Email"));
                nv.setCccd(rs.getString("CCCD"));
                nv.setChucVu(rs.getString("TenRole"));
                nv.setMaTrangThai(rs.getString("MaTrangThai"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nv;
    }


    public boolean isPhoneExists(String sdt) {

        String sql = "SELECT COUNT(*) FROM NHANVIEN WHERE SDT=?";

        try {
            Connection con = new ConnectService().myConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sdt);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Kiểm tra CCCD đã tồn tại (Thêm)
    public boolean isCccdExists(String cccd) {

        String sql = "SELECT COUNT(*) FROM NHANVIEN WHERE CCCD=?";

        try {
            Connection con = new ConnectService().myConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cccd);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    public boolean isPhoneExistsForUpdate(String sdt, int maNV) {

        String sql = "SELECT COUNT(*) FROM NHANVIEN WHERE SDT=? AND MaNV<>?";

        try {
            Connection con = new ConnectService().myConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sdt);
            ps.setInt(2, maNV);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Kiểm tra CCCD đã tồn tại khi Sửa
    public boolean isCccdExistsForUpdate(String cccd, int maNV) {

        String sql = "SELECT COUNT(*) FROM NHANVIEN WHERE CCCD=? AND MaNV<>?";

        try {
            Connection con = new ConnectService().myConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cccd);
            ps.setInt(2, maNV);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public NhanVien findById(int maNV) {

        NhanVien nv = null;

        String sql = "SELECT * FROM NHANVIEN WHERE MaNV = ?";

        try {

            ConnectService service = new ConnectService();
            Connection con = service.myConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, maNV);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                nv = new NhanVien();

                nv.setMaNV(rs.getInt("MaNV"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setQuocTich(rs.getString("QuocTich"));
                nv.setQueQuan(rs.getString("QueQuan"));
                nv.setNoiThuongTru(rs.getString("NoiThuongTru"));
                nv.setSdt(rs.getString("SDT"));
                nv.setEmail(rs.getString("Email"));
                nv.setCccd(rs.getString("CCCD"));
                nv.setMaTrangThai(rs.getString("MaTrangThai"));
                nv.setMaRole(rs.getString("MaRole"));
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nv;
    }
}