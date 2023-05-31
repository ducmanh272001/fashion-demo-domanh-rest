package com.fashion.service.product;

import java.text.Normalizer;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fashion.config.ConfigFactory;
import com.fashion.entity.ProductEntity;

public class ProductServiceImpl implements ProductService<ProductEntity> {

	private SessionFactory FACTORY;

	public static ProductServiceImpl getNewSanPham() {
		return new ProductServiceImpl();
	}

	private ProductServiceImpl() {
		FACTORY = ConfigFactory.getSessionFactory();
		if (FACTORY == null) {
			System.out.println("Lỗi Không có kết nối với biến SessionFactory");
		}
	}

	// Lấy cái áo

	@Override
	public List<ProductEntity> selectAll() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity").setMaxResults(10).list();
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
	public List<ProductEntity> selectAllAscending() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity order by price_new").list();
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
	public List<ProductEntity> selectAllDecrease() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity order by price_new desc").list();
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
	public List<ProductEntity> gia100den200() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss
					.createQuery("from ProductEntity where price_new >= 100000 and price_new <= 200000")
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
	public List<ProductEntity> gia200den300() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss
					.createQuery("from ProductEntity where price_new >= 200000 and price_new <= 300000")
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
	public List<ProductEntity> gia300den500() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss
					.createQuery("from ProductEntity where price_new >= 300000 and price_new <= 500000")
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
	public List<ProductEntity> giaTren500() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where price_new > 500000").setMaxResults(8)
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
	public List<ProductEntity> phanTrangAo(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where idlsp = 3").setMaxResults(8)
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
	public List<ProductEntity> phanTrangAoTangDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where idlsp = 3 order by price_new")
					.setMaxResults(8).setFirstResult((page - 1) * 8).getResultList();
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
	public List<ProductEntity> phanTrangAoGiamDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where idlsp = 3 order by price_new desc")
					.setMaxResults(8).setFirstResult((page - 1) * 8).getResultList();
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
	public List<ProductEntity> phanTrangDamGiamDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where idlsp = 2 order by price_new desc")
					.setMaxResults(8).setFirstResult((page - 1) * 8).getResultList();
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
	public List<ProductEntity> phanTrangDamTangDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where idlsp = 2 order by price_new")
					.setMaxResults(8).setFirstResult((page - 1) * 8).getResultList();
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
	public List<ProductEntity> phanTrangQuanTangDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where idlsp = 4 order by price_new")
					.setMaxResults(8).setFirstResult((page - 1) * 8).getResultList();
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
	public List<ProductEntity> phanTrangQuanGiamDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where idlsp = 4 order by price_new desc")
					.setMaxResults(8).setFirstResult((page - 1) * 8).getResultList();
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
	public List<ProductEntity> phanTrangAoDaiTangDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where idlsp = 5 order by price_new")
					.setMaxResults(8).setFirstResult((page - 1) * 8).getResultList();
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
	public List<ProductEntity> phanTrangAoDaiGiamDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where idlsp = 5 order by price_new desc")
					.setMaxResults(8).setFirstResult((page - 1) * 8).getResultList();
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
	public List<ProductEntity> phanTrangCaBoGiamDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where idlsp = 1 order by price_new desc")
					.setMaxResults(8).setFirstResult((page - 1) * 8).getResultList();
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
	public List<ProductEntity> phanTrangCaBoTangDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where idlsp = 1 order by price_new")
					.setMaxResults(8).setFirstResult((page - 1) * 8).getResultList();
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	public List<ProductEntity> phanTrang(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Chia 6 trang sản phẩm
			int max = 8;
			List<ProductEntity> lst = ss.createQuery("from ProductEntity order by id desc").setMaxResults(max)
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

	// Phân trang tất cả sản phẩm tăng dần
	public List<ProductEntity> phanTrangSanPhamTangDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Chia 6 trang sản phẩm
			int max = 8;
			List<ProductEntity> lst = ss.createQuery("from ProductEntity order by price_new").setMaxResults(max)
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
	public List<ProductEntity> phanTrangSanPhamGiamDan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Chia 6 trang sản phẩm
			int max = 8;
			List<ProductEntity> lst = ss.createQuery("from ProductEntity order by price_new desc").setMaxResults(max)
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
	public List<ProductEntity> sanPhamMoiNhat() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity order by id desc").setMaxResults(12)
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

	// Lấy ra 4 sản phẩm mới của áo
	public List<ProductEntity> sanPhamAoMoiNhat() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where idlsp = 3 order by id desc")
					.setMaxResults(4).getResultList();
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
	public List<ProductEntity> sanPhamQuanMoiNhat() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where idlsp = 4 order by id desc")
					.setMaxResults(4).getResultList();
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
	public List<ProductEntity> sanPhamAoDaiMoiNhat() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where idlsp = 5 order by id desc")
					.setMaxResults(4).getResultList();
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
	public List<ProductEntity> sanPhamDamMoiNhat() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where idlsp = 2 order by id desc")
					.setMaxResults(4).getResultList();
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
	public List<ProductEntity> sanPhamCaBoMoiNhat() {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where idlsp = 1 order by id desc")
					.setMaxResults(4).getResultList();
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

	public List<ProductEntity> phanTrangQuan(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Chia 6 trang sản phẩm
			int max = 8;
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where idlsp = 4").setMaxResults(max)
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
	public List<ProductEntity> phanTrangDam(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Chia 6 trang sản phẩm
			int max = 8;
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where idlsp = 2").setMaxResults(max)
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
	public List<ProductEntity> phanTrangCaBo(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Chia 8 trang sản phẩm
			int max = 8;
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where idlsp = 1").setMaxResults(max)
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
	public List<ProductEntity> phanTrangAoDai(int page) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			// Chia 8 trang sản phẩm
			int max = 8;
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where idlsp = 5").setMaxResults(max)
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

	public List<ProductEntity> selectDam() {
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> list = ss.createQuery("from ProductEntity where idlsp = 2").list();
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

	public List<ProductEntity> selectCaBo() {
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> list = ss.createQuery("from ProductEntity where idlsp = 1").list();
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
	public List<ProductEntity> selectAo() {
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> list = ss.createQuery("from ProductEntity where idlsp = 3").list();
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

	public List<ProductEntity> selectAoDai() {
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> list = ss.createQuery("from ProductEntity where idlsp = 5").list();
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

	public List<ProductEntity> selectQuan() {
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> list = ss.createQuery("from ProductEntity where idlsp = 4").list();
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

	public List<ProductEntity> sanPhamSale() {
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> lst = ss
					.createQuery("from ProductEntity where price_new is not null and price_new < price_old")
					.setMaxResults(10).list();
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
	public ProductEntity selectById(int id) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {

			ss.beginTransaction();
			ProductEntity sp = ss.get(ProductEntity.class, id);
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
	public boolean insert(ProductEntity t) {
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
	public boolean update(ProductEntity t) {
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
	public List<ProductEntity> selectByName(String idtim) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			if (idtim == null) {
				System.out.println("Xin vui lòng điền giá trị");
			} else {
				idtim = "%" + idtim.trim() + "%";
			}
			List<ProductEntity> lst = ss.createQuery("from ProductEntity where name like :namela")
					.setParameter("namela", idtim).list();
			if (Objects.isNull(lst)) {
				String search = this.removeAccent("%" + idtim.toLowerCase().trim() + "%");
				List<ProductEntity> lok = ss
						.createQuery("from ProductEntity where lower(unaccent(name)) like lower(unaccent(:namela))")
						.setParameter("namela", search).list();
				return lok;
			}
			return lst;
		} catch (Exception e) {
			System.out.println("Lỗi Truy Vấn");
			ss.getTransaction().rollback();
		} finally {
			ss.close();
		}
		return null;
	}

	public static String removeAccent(String str) {
		try {
			String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
			Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
			return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll("đ", "d");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	@Override
	public boolean delete(int idxoa) {
		// Mở 1 biến session
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			int idXOA = ss.createQuery("delete from ProductEntity where id = :idxoa").setParameter("idxoa", idxoa)
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
			List<Long> list = ss.createQuery("select count(*) from ProductEntity").list();
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
			List<Long> list = ss.createQuery("select count(*) from ProductEntity where idlsp = 3").list();
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
			List<Long> list = ss.createQuery("select count(*) from ProductEntity where idlsp = 4").list();
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
			List<Long> list = ss.createQuery("select count(*) from ProductEntity where idlsp = 2").list();
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
			List<Long> list = ss.createQuery("select count(*) from ProductEntity where idlsp = 5").list();
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
			List<Long> list = ss.createQuery("select count(*) from ProductEntity where idlsp = 1").list();
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
	public List<ProductEntity> selectByBrand(int idla, int ptrang) {
		// Bắt đầu 1 phiên làm việc
		Session ss = FACTORY.openSession();
		try {
			ss.beginTransaction();
			List<ProductEntity> list = ss.createQuery("from ProductEntity where idnh = " + idla).setMaxResults(8)
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
			List<Long> list = ss.createQuery("select count(*) from ProductEntity where idnh = " + idla).getResultList();
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
