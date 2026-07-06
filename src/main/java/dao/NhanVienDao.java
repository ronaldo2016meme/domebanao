package dao;

import model.NhanVien;
import service.ConnectService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDao {

    public List<NhanVien> getAll() {

        List<NhanVien> list = new ArrayList<>();

        String sql = "SELECT * FROM NHANVIEN";

        try {

            Connection con = new ConnectService().myConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                NhanVien nv = new NhanVien();

                nv.setMaNV(rs.getInt("MaNV"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setSdt(rs.getString("SDT"));
                nv.setEmail(rs.getString("Email"));
                nv.setCccd(rs.getString("CCCD"));
                nv.setMaTrangThai(rs.getString("MaTrangThai"));

                list.add(nv);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void insert(NhanVien nv){

        String sql = "INSERT INTO NHANVIEN(HoTen,SDT,Email,CCCD,MaTrangThai) VALUES(?,?,?,?,?)";

        try{

            Connection con = new ConnectService().myConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nv.getHoTen());
            ps.setString(2, nv.getSdt());
            ps.setString(3, nv.getEmail());
            ps.setString(4, nv.getCccd());
            ps.setString(5, nv.getMaTrangThai());

            ps.executeUpdate();

            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }
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
                nv.setSdt(rs.getString("SDT"));
                nv.setEmail(rs.getString("Email"));
                nv.setCccd(rs.getString("CCCD"));
                nv.setMaTrangThai(rs.getString("MaTrangThai"));
            }

            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return nv;
    }
    public void update(NhanVien nv){

        String sql="UPDATE NHANVIEN SET HoTen=?,SDT=?,Email=?,CCCD=?,MaTrangThai=? WHERE MaNV=?";

        try{

            Connection con=new ConnectService().myConnection();

            PreparedStatement ps=con.prepareStatement(sql);

            ps.setString(1,nv.getHoTen());
            ps.setString(2,nv.getSdt());
            ps.setString(3,nv.getEmail());
            ps.setString(4,nv.getCccd());
            ps.setString(5,nv.getMaTrangThai());
            ps.setInt(6,nv.getMaNV());

            ps.executeUpdate();

            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public NhanVien findByMaNV(int maNV) {

        NhanVien nv = null;

        String sql = "SELECT * FROM NHANVIEN WHERE MaNV=?";

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
                nv.setMaTrangThai(rs.getString("MaTrangThai"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nv;
    }
}