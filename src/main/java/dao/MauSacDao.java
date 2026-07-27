package dao;

import model.MauSac;
import service.ConnectService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MauSacDao {
    public List<MauSac> getAll() {
        List<MauSac> list = new ArrayList<>();

        String sql = "SELECT * FROM MauSac";

        ConnectService cs = new ConnectService();

        try (Connection con = cs.myConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MauSac ms = new MauSac();
                ms.setMaMau(rs.getString("MaMau"));
                ms.setTenMau(rs.getString("TenMau"));
                list.add(ms);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
