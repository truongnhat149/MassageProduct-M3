package com.shopmall.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.shopmall.dto.ProductDto;
import com.shopmall.dto.SearchProductObject;
import com.shopmall.entities.QProduct;
import com.shopmall.entities.Product;
import com.shopmall.repository.CategoryRepository;
import com.shopmall.repository.ManufacturerRepository;
import com.shopmall.repository.ProductRepository;
import com.shopmall.service.ProductService;
import com.querydsl.core.BooleanBuilder;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ManufacturerRepository manufacturerRepo;

	// đổi từ ProductDto sang đối tượng Product để add vào db
	public Product convertFromProductDto(ProductDto dto) {
		Product product = new Product();
		if (!dto.getId().equals("")) {
			product.setId(Long.parseLong(dto.getId()));
		}
		product.setNameProduct(dto.getNameProduct());
		product.setCategory(categoryRepo.findById(dto.getCategoryId()).get());
		product.setManufacturer(manufacturerRepo.findById(dto.getNhaSXId()).get());
		product.setUnitPrice(Long.parseLong(dto.getUnitPrice()));
		product.setThietKe(dto.getThietKe());
		product.setThongTinBaoHanh(dto.getThongTinBaoHanh());
		product.setGenaralInfomation(dto.getGenaralInfomation());
		product.setDonViKho(Integer.parseInt(dto.getDonViKho()));

		return product;
	}

	@Override
	public Product save(ProductDto dto) {
		Product sp = convertFromProductDto(dto);
		System.out.println(sp);
		return productRepo.save(sp);
	}

	@Override
	public Product update(ProductDto dto) {
		return productRepo.save(convertFromProductDto(dto));
	}

	@Override
	public void deleteById(long id) {
		productRepo.deleteById(id);

	}

	@Override
	public Page<Product> getAllProductByFilter(SearchProductObject object, int page, int limit) {
		BooleanBuilder builder = new BooleanBuilder();
		String price = object.getUnitPrice();

		// sắp xếp theo giá
		Sort sort = Sort.by(Direction.ASC, "unitPrice"); // mặc định tăng dần
		if (object.getSapXepTheoGia().equals("desc")) { // giảm dần
			sort = Sort.by(Direction.DESC, "unitPrice");
		}

		if (!object.getCategoryId().equals("") && object.getCategoryId() != null) {
			builder.and(QProduct.product.category.eq(categoryRepo.findById(Long.parseLong(object.getCategoryId())).get()));
		}

		if (!object.getHangSXId().equals("") && object.getHangSXId() != null) {
			builder.and(QProduct.product.manufacturer
					.eq(manufacturerRepo.findById(Long.parseLong(object.getHangSXId())).get()));
		}

		// tim theo don gia
		switch (price) {
		case "duoi-2-trieu":
			builder.and(QProduct.product.unitPrice.lt(2000000));
			break;

		case "2-trieu-den-4-trieu":
			builder.and(QProduct.product.unitPrice.between(2000000, 4000000));
			break;

		case "4-trieu-den-6-trieu":
			builder.and(QProduct.product.unitPrice.between(4000000, 6000000));
			break;

		case "6-trieu-den-10-trieu":
			builder.and(QProduct.product.unitPrice.between(6000000, 10000000));
			break;

		case "tren-10-trieu":
			builder.and(QProduct.product.unitPrice.gt(10000000));
			break;

		default:
			break;
		}
		return productRepo.findAll(builder, PageRequest.of(page, limit, sort));
	}

	@Override
	public List<Product> getLatestProduct() {
		return productRepo.findFirst12ByCategoryNameCategoryContainingIgnoreCaseOrderByIdDesc("Massage");
	}

	public Iterable<Product> getProductByNameProductWithoutPaginate(SearchProductObject object) {
		BooleanBuilder builder = new BooleanBuilder();
		int resultPerPage = 12;
		String[] keywords = object.getKeyword();
		String sort = object.getSort();
		String price = object.getUnitPrice();

		// Keyword
		builder.and(QProduct.product.nameProduct.like("%" + keywords[0] + "%"));
		if (keywords.length > 1) {
			for (int i = 1; i < keywords.length; i++) {
				builder.and(QProduct.product.nameProduct.like("%" + keywords[i] + "%"));
			}
		}
		// Muc gia
		switch (price) {
		case "duoi-2-trieu":
			builder.and(QProduct.product.unitPrice.lt(2000000));
			break;

		case "2-trieu-den-4-trieu":
			builder.and(QProduct.product.unitPrice.between(2000000, 4000000));
			break;

		case "4-trieu-den-6-trieu":
			builder.and(QProduct.product.unitPrice.between(4000000, 6000000));
			break;

		case "6-trieu-den-10-trieu":
			builder.and(QProduct.product.unitPrice.between(6000000, 10000000));
			break;

		case "tren-10-trieu":
			builder.and(QProduct.product.unitPrice.gt(10000000));
			break;

		default:
			break;
		}
		return productRepo.findAll(builder);
	}

	@Override
	public Product getProductById(long id) {
		return productRepo.findById(id).get();
	}

	// Tim kiem san pham theo keyword, sap xep, phan trang, loc theo muc gia, lay 12
	// san pham moi trang
	@Override
	public Page<Product> getProductByNameProduct(SearchProductObject object, int page, int resultPerPage) {
		BooleanBuilder builder = new BooleanBuilder();
//		int resultPerPage = 12;
		String[] keywords = object.getKeyword();
		String sort = object.getSort();
		String price = object.getUnitPrice();
		String brand = object.getBrand();
		String manufactor = object.getManufactor();
		// Keyword
		builder.and(QProduct.product.nameProduct.like("%" + keywords[0] + "%"));
		if (keywords.length > 1) {
			for (int i = 1; i < keywords.length; i++) {
				builder.and(QProduct.product.nameProduct.like("%" + keywords[i] + "%"));
			}
		}
		// Muc gia
		switch (price) {
		case "duoi-2-trieu":
			builder.and(QProduct.product.unitPrice.lt(2000000));
			break;

		case "2-trieu-den-4-trieu":
			builder.and(QProduct.product.unitPrice.between(2000000, 4000000));
			break;

		case "4-trieu-den-6-trieu":
			builder.and(QProduct.product.unitPrice.between(4000000, 6000000));
			break;

		case "6-trieu-den-10-trieu":
			builder.and(QProduct.product.unitPrice.between(6000000, 10000000));
			break;

		case "tren-10-trieu":
			builder.and(QProduct.product.unitPrice.gt(10000000));
			break;

		default:
			break;
		}

		// Danh muc va hang san xuat
		if (brand.length()>1) {
			builder.and(QProduct.product.category.nameCategory.eq(brand));
		}
		if (manufactor.length()>1) {
			builder.and(QProduct.product.manufacturer.nameManufacturer.eq(manufactor));
		}

		// Sap xep
		if (sort.equals("newest")) {
			return productRepo.findAll(builder, PageRequest.of(page - 1, resultPerPage, Sort.Direction.DESC, "id"));
		} else if (sort.equals("priceAsc")) {
			return productRepo.findAll(builder, PageRequest.of(page - 1, resultPerPage, Sort.Direction.ASC, "unitPrice"));
		} else if (sort.equals("priceDes")) {
			return productRepo.findAll(builder, PageRequest.of(page - 1, resultPerPage, Sort.Direction.DESC, "unitPrice"));
		}
		return productRepo.findAll(builder, PageRequest.of(page - 1, resultPerPage));
	}

	public List<Product> getAllProductByList(Set<Long> idList) {
		return productRepo.findByIdIn(idList);
	}

	@Override
	public Page<Product> getProductByNameProductForAdmin(String NameProduct, int page, int size) {
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(QProduct.product.nameProduct.like("%" + NameProduct + "%"));
		return productRepo.findAll(builder, PageRequest.of(page, size));
	}
	
	
	@Override
	public Iterable<Product> getProductByNameCategory(String brand) {
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(QProduct.product.category.nameCategory.eq(brand));
		return productRepo.findAll(builder);
	}
	
	@Override
	public Page<Product> getProductByBrand(SearchProductObject object, int page, int resultPerPage) {
		BooleanBuilder builder = new BooleanBuilder();
		String price = object.getUnitPrice();
		String brand = object.getBrand();
		String manufactor = object.getManufactor();

		// Muc gia
		switch (price) {
		case "duoi-2-trieu":
			builder.and(QProduct.product.unitPrice.lt(2000000));
			break;

		case "2-trieu-den-4-trieu":
			builder.and(QProduct.product.unitPrice.between(2000000, 4000000));
			break;

		case "4-trieu-den-6-trieu":
			builder.and(QProduct.product.unitPrice.between(4000000, 6000000));
			break;

		case "6-trieu-den-10-trieu":
			builder.and(QProduct.product.unitPrice.between(6000000, 10000000));
			break;

		case "tren-10-trieu":
			builder.and(QProduct.product.unitPrice.gt(10000000));
			break;

		default:
			break;
		}

		// Danh muc va hang san xuat
		if (brand.length()>1) {
			builder.and(QProduct.product.category.nameCategory.eq(brand));
		}
		if (manufactor.length()>1) {
			builder.and(QProduct.product.manufacturer.nameManufacturer.eq(manufactor));
		}

		return productRepo.findAll(builder, PageRequest.of(page - 1, resultPerPage));
	}
}
