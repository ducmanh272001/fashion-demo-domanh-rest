package com.fashion.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.configFactory.ConfigFactory;
import com.fashion.entity.Sanpham;

public class SanPhamImpl implements ImplDao<Sanpham> {

	private SessionFactory FACTORY;

	public static SanPhamImpl getNewSanPham() {
		return new SanPhamImpl();
	}

	private SanPhamImpl() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Lỗi Không có kết nối với biến SessionFactory");
		}
	}

	// Lấy cái áo

	@Override
	public List<Sanpham> selectAll() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham").list();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy sản phẩm tăng dần
	public List<Sanpham> selectAllAscending() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham order by price_new").list();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy sản phẩm giảm dần
	public List<Sanpham> selectAllDecrease() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham order by price_new desc").list();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy sản phẩm có giá từ 100 - 200đ
	public List<Sanpham> gia100den200() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham where price_new >= 100000 and price_new <= 200000")
					.setMaxResults(8).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy sản phẩm có giá từ 200 - 300đ
	public List<Sanpham> gia200den300() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham where price_new >= 200000 and price_new <= 300000")
					.setMaxResults(8).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy sản phẩm có giá từ 300 - 500đ
	public List<Sanpham> gia300den500() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham where price_new >= 300000 and price_new <= 500000")
					.setMaxResults(8).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy sản phẩm có giá trên 500đ
	public List<Sanpham> giaTren500() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham where price_new > 500000").setMaxResults(8)
					.getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy số lượng sản phẩm của áo
	public List<Sanpham> phanTrangAo(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham where idlsp = 3").setMaxResults(8)
					.setFirstResult((page - 1) * 8).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy số lượng sản phẩm của áo lấy theo giá tăng dần
	public List<Sanpham> phanTrangAoTangDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham where idlsp = 3 order by price_new").setMaxResults(8)
					.setFirstResult((page - 1) * 8).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy số lượng sản phẩm của áo lấy theo giá giảm dần
	public List<Sanpham> phanTrangAoGiamDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham where idlsp = 3 order by price_new desc").setMaxResults(8)
					.setFirstResult((page - 1) * 8).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Phân trang đầm giảm dần
	// Lấy số lượng sản phẩm của áo lấy theo giá giảm dần
	public List<Sanpham> phanTrangDamGiamDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham where idlsp = 2 order by price_new desc").setMaxResults(8)
					.setFirstResult((page - 1) * 8).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Phân trang đầm tăng dần
	public List<Sanpham> phanTrangDamTangDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham where idlsp = 2 order by price_new").setMaxResults(8)
					.setFirstResult((page - 1) * 8).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Phân trang quần tăng dần
	public List<Sanpham> phanTrangQuanTangDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham where idlsp = 4 order by price_new").setMaxResults(8)
					.setFirstResult((page - 1) * 8).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Phân trang quần tăng dần
	public List<Sanpham> phanTrangQuanGiamDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham where idlsp = 4 order by price_new desc").setMaxResults(8)
					.setFirstResult((page - 1) * 8).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Phân trang áo dài tăng dần
	public List<Sanpham> phanTrangAoDaiTangDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham where idlsp = 5 order by price_new").setMaxResults(8)
					.setFirstResult((page - 1) * 8).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Phân trang áo dài giảm dần
	public List<Sanpham> phanTrangAoDaiGiamDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham where idlsp = 5 order by price_new desc").setMaxResults(8)
					.setFirstResult((page - 1) * 8).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Phân trang cả bộ giảm dần
	public List<Sanpham> phanTrangCaBoGiamDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham where idlsp = 1 order by price_new desc").setMaxResults(8)
					.setFirstResult((page - 1) * 8).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Phân trang cả bộ giảm dần
	public List<Sanpham> phanTrangCaBoTangDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham where idlsp = 1 order by price_new").setMaxResults(8)
					.setFirstResult((page - 1) * 8).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	public List<Sanpham> phanTrang(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Chia 6 trang sản phẩm
			int max = 8;
			List<Sanpham> lst = ss.createQuery("from Sanpham").setMaxResults(max).setFirstResult((page - 1) * max)
					.getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Phân trang tất cả sản phẩm tăng dần
	public List<Sanpham> phanTrangSanPhamTangDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Chia 6 trang sản phẩm
			int max = 8;
			List<Sanpham> lst = ss.createQuery("from Sanpham order by price_new").setMaxResults(max)
					.setFirstResult((page - 1) * max).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Phân trang tất cả sản phẩm giảm dần
	public List<Sanpham> phanTrangSanPhamGiamDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Chia 6 trang sản phẩm
			int max = 8;
			List<Sanpham> lst = ss.createQuery("from Sanpham order by price_new desc").setMaxResults(max)
					.setFirstResult((page - 1) * max).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy ra 12 sản phẩm mới nhất
	public List<Sanpham> sanPhamMoiNhat() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham order by id desc").setMaxResults(12).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy ra 4 sản phẩm mới của áo
	public List<Sanpham> sanPhamAoMoiNhat() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham where idlsp = 3 order by id desc").setMaxResults(4)
					.getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy ra 4 sản phẩm mới của quần
	public List<Sanpham> sanPhamQuanMoiNhat() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham where idlsp = 4 order by id desc").setMaxResults(4)
					.getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy ra 4 sản phẩm mới của áo dài
	public List<Sanpham> sanPhamAoDaiMoiNhat() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham where idlsp = 5 order by id desc").setMaxResults(4)
					.getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy ra 4 sản phẩm mới của dam
	public List<Sanpham> sanPhamDamMoiNhat() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham where idlsp = 2 order by id desc").setMaxResults(4)
					.getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy ra 4 sản phẩm mới của cả bộ
	public List<Sanpham> sanPhamCaBoMoiNhat() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> lst = ss.createQuery("from Sanpham where idlsp = 1 order by id desc").setMaxResults(4)
					.getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy list sản phẩm của Quần

	public List<Sanpham> phanTrangQuan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Chia 6 trang sản phẩm
			int max = 8;
			List<Sanpham> lst = ss.createQuery("from Sanpham where idlsp = 4").setMaxResults(max)
					.setFirstResult((page - 1) * max).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Phân trang đầm
	public List<Sanpham> phanTrangDam(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Chia 6 trang sản phẩm
			int max = 8;
			List<Sanpham> lst = ss.createQuery("from Sanpham where idlsp = 2").setMaxResults(max)
					.setFirstResult((page - 1) * max).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Phân trang Cả Bộ
	public List<Sanpham> phanTrangCaBo(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Chia 8 trang sản phẩm
			int max = 8;
			List<Sanpham> lst = ss.createQuery("from Sanpham where idlsp = 1").setMaxResults(max)
					.setFirstResult((page - 1) * max).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Phân trang Aó dài
	public List<Sanpham> phanTrangAoDai(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Chia 8 trang sản phẩm
			int max = 8;
			List<Sanpham> lst = ss.createQuery("from Sanpham where idlsp = 5").setMaxResults(max)
					.setFirstResult((page - 1) * max).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy cái đầm

	public List<Sanpham> selectDam() {
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> list = ss.createQuery("from Sanpham where idlsp = 2").list();
			return list;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy set cả bộ

	public List<Sanpham> selectCaBo() {
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> list = ss.createQuery("from Sanpham where idlsp = 1").list();
			return list;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy áo
	public List<Sanpham> selectAo() {
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> list = ss.createQuery("from Sanpham where idlsp = 3").list();
			return list;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy set cả bộ

	public List<Sanpham> selectAoDai() {
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> list = ss.createQuery("from Sanpham where idlsp = 5").list();
			return list;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy set cả bộ

	public List<Sanpham> selectQuan() {
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> list = ss.createQuery("from Sanpham where idlsp = 4").list();
			return list;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	// Sắp xếp theo sản phẩm mới nhất
	public List<Sanpham> sanPhamSale() {
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> list = ss.createQuery("from Sanpham where idsp >=40 order by id desc").list();
			return list;
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi truy vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public Sanpham selectById(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			Sanpham sp = ss.get(Sanpham.class, id);
			return sp;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public boolean insert(Sanpham t) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			ss.save(t);
			System.out.println("Bạn đã thêm id sản phẩm là " + t.getId() + " vào cơ sở dữ liệu");
			ss.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return false;
	}

	@Override
	public boolean update(Sanpham t) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			ss.update(t);
			System.out.println("Bạn đã sửa id sản phẩm là " + t.getId() + " vào cơ sở dữ liệu");
			ss.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return false;
	}

	@Override
	public List<Sanpham> selectByName(String idtim) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			if (idtim == null) {
				System.out.println("Xin vui lòng điền giá trị");
			} else {
				idtim = "%" + idtim + "%";
			}
			List<Sanpham> lst = ss.createQuery("from Sanpham where name like :namela").setParameter("namela", idtim)
					.list();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public boolean delete(int idxoa) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			int idXOA = ss.createQuery("delete from Sanpham where id = :idxoa").setParameter("idxoa", idxoa)
					.executeUpdate();
			ss.getTransaction().commit();
			if (idXOA > 0) {
				System.out.println("Bạn đã xóa thành công");
				return true;
			} else {
				System.out.println("Không có cơ sở dữ liệu để xóa");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return false;
	}

	/// Lấy ra csai số lượng sản phẩm ở đây

	// NÓ không gán giá trị

	public Long selectCount() {
		// Bắt đầu 1 phiên làm việc
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Vì trả về 1 cái nên không cần commit
			List<Long> list = ss.createQuery("select count(*) from Sanpham").list();
			return list.get(0);
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Lỗi câu lệnh try vấn");
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy số lượng áo
	public Long selectCountAo() {
		// Bắt đầu 1 phiên làm việc
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Vì trả về 1 cái nên không cần commit
			List<Long> list = ss.createQuery("select count(*) from Sanpham where idlsp = 3").list();
			return list.get(0);
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Lỗi câu lệnh try vấn");
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy số lượng quần
	public Long selectCountQuan() {
		// Bắt đầu 1 phiên làm việc
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Vì trả về 1 cái nên không cần commit
			List<Long> list = ss.createQuery("select count(*) from Sanpham where idlsp = 4").list();
			return list.get(0);
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Lỗi câu lệnh try vấn");
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy số lượng Đầm
	public Long selectCountDam() {
		// Bắt đầu 1 phiên làm việc
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Vì trả về 1 cái nên không cần commit
			List<Long> list = ss.createQuery("select count(*) from Sanpham where idlsp = 2").list();
			return list.get(0);
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Lỗi câu lệnh try vấn");
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy số lượng ÁO DÀI
	public Long selectCoutAoDai() {
		// Bắt đầu 1 phiên làm việc
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Vì trả về 1 cái nên không cần commit
			List<Long> list = ss.createQuery("select count(*) from Sanpham where idlsp = 5").list();
			return list.get(0);
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Lỗi câu lệnh try vấn");
		} finally {
			ss.close();
		}
		return null;
	}

	// Lấy số lượng ÁO DÀI
	public Long selectCoutCaBo() {
		// Bắt đầu 1 phiên làm việc
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Vì trả về 1 cái nên không cần commit
			List<Long> list = ss.createQuery("select count(*) from Sanpham where idlsp = 1").list();
			return list.get(0);
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Lỗi câu lệnh try vấn");
		} finally {
			ss.close();
		}
		return null;
	}

	// Tìm theo nhãn hiệu
	public List<Sanpham> selectByBrand(int idla, int ptrang) {
		// Bắt đầu 1 phiên làm việc
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Sanpham> list = ss.createQuery("from Sanpham where idnh = " + idla).setMaxResults(8)
					.setFirstResult((ptrang - 1) * 8).getResultList();
			return list;
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println("Lỗi câu lệnh try vấn");
		} finally {
			ss.close();
		}
		return null;
	}

	public Long selectSLBrand(int idla) {
		// Bắt đầu 1 phiên làm việc
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<Long> list = ss.createQuery("select count(*) from Sanpham where idnh = " + idla).getResultList();
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
