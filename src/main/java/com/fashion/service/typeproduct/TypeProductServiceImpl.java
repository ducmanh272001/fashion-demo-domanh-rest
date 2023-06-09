package com.fashion.service.typeproduct;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.config.ConfigFactory;
import com.fashion.entity.TypeProductEntity;

public class TypeProductServiceImpl implements TypeProductService<TypeProductEntity> {

	private SessionFactory FACTORY;

	// Goin tạo biến new cho LoaiSpImpl
	public static TypeProductServiceImpl getNewLoaiSp() {
		return new TypeProductServiceImpl();
	}

	private TypeProductServiceImpl() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Lỗi không có FACTORY");
		}
	}

	@Override
	public List<TypeProductEntity> selectAll() {
		// mỞ sesion ra
		Session ss = FACTORY.openSession();
		try {
			// Bắt đầu 1 phiên làm việc
			ss.beginTransaction();
			// Vì là lấy ra dữ liệu nên sẽ ko cần commit
			List<TypeProductEntity> lst = ss.createQuery("from TypeProductEntity order by id desc").list();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi câu lệnh truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public TypeProductEntity selectById(int id) {
		// mỞ sesion ra
		Session ss = FACTORY.openSession();
		try {
			// Bắt đầu 1 phiên làm việc
			ss.beginTransaction();
			// Vì là lấy ra dữ liệu nên sẽ ko cần commit
			TypeProductEntity lsp = ss.get(TypeProductEntity.class, id);
			return lsp;
		} catch (Exception e) {
			System.out.println("Lỗi câu lệnh truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public boolean insert(TypeProductEntity t) {
		// mỞ sesion ra
		Session ss = FACTORY.openSession();
		try {
			// Bắt đầu 1 phiên làm việc
			ss.beginTransaction();
			ss.save(t);
			System.out.println("Bạn đã thêm id loaisp Là " + t.getId() + " Vào cơ sở dữ liệu");
			ss.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("Lỗi câu lệnh truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return false;
	}

	@Override
	public boolean update(TypeProductEntity t) {
		// mỞ sesion ra
		Session ss = FACTORY.openSession();
		try {
			// Bắt đầu 1 phiên làm việc
			ss.beginTransaction();
			ss.update(t);
			System.out.println("Bạn đã sửa id loaisp Là " + t.getId() + " Vào cơ sở dữ liệu");
			ss.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("Lỗi câu lệnh truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return false;
	}

	@Override
	public List<TypeProductEntity> selectByName(String idtim) {
		// mỞ sesion ra
		Session ss = FACTORY.openSession();
		try {
			// Bắt đầu 1 phiên làm việc
			ss.beginTransaction();
			// Vì là lấy ra dữ liệu nên sẽ ko cần commit
			if (idtim == null) {
				System.out.println("Điền tên cần tìm vào");
			} else {
				idtim = "%" + idtim + "%";
			}
			List<TypeProductEntity> lst = ss.createQuery("from TypeProductEntity where loai_sp like :tenla")
					.setParameter("tenla", idtim).list();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi câu lệnh truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public boolean delete(int idxoa) {
		// mỞ sesion ra
		Session ss = FACTORY.openSession();
		try {
			// Bắt đầu 1 phiên làm việc
			ss.beginTransaction();
			int idXoa = ss.createQuery("delete from TypeProductEntity where id = :idxoa").setParameter("idxoa", idxoa)
					.executeUpdate();
			System.out.println("Bạn đã xóa id là " + idxoa + " ra khỏi cơ sở dữ liệu");
			ss.getTransaction().commit();
			if (idxoa > 0) {
				return true;
			} else {
				System.out.println("Không xóa được");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Lỗi câu lệnh truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return false;
	}

	// Phân trang
	public List<TypeProductEntity> phanTrangLoaiSanPham(int sotrang) {
		// mỞ sesion ra
		Session ss = FACTORY.openSession();
		try {
			// Bắt đầu 1 phiên làm việc
			ss.beginTransaction();
			// Vì là lấy ra dữ liệu nên sẽ ko cần commit
			List<TypeProductEntity> lst = ss.createQuery("from TypeProductEntity order by id desc").setMaxResults(8)
					.setFirstResult((sotrang - 1) * 8).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi câu lệnh truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Số lượng danh mục
	public Long selectCount() {
		// Bắt đầu 1 phiên làm việc
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Vì trả về 1 cái nên không cần commit
			List<Long> list = ss.createQuery("select count(*) from TypeProductEntity").list();
			return list.get(0);
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Lỗi câu lệnh try vấn");
		} finally {
			ss.close();
		}
		return null;
	}
}
