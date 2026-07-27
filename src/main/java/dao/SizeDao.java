package dao;

import model.Size;
import service.ConnectService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SizeDao {

    private ConnectService cs = new ConnectService();

    public List<Size> getAll() {

        List<Size> list = new ArrayList<>();

        String sql = "SELECT * FROM Size";

        try (Connection con = cs.myConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Size s = new Size();

                s.setMaSize(rs.getString("MaSize"));
                s.setTenSize(rs.getString("TenSize"));

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}