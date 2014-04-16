/**
 * 
 */
package edu.iiitb.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.JSONPopulator;
import org.apache.struts2.json.JSONUtil;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DBHandlerForAdmin;
import edu.iiitb.database.DBHandlerForCart;
import edu.iiitb.model.CartCookie;
import edu.iiitb.model.CartModel;
import edu.iiitb.model.CartProduct;
import edu.iiitb.model.User;

/**
 * @author PrashantN
 * 
 */
public class CartManager extends ActionSupport implements SessionAware,
		ServletResponseAware, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userid;
	private int productId;
	private int quantity;
	private ArrayList<CartModel> products;
	private int count;
	private Map session;
	private HttpServletResponse servletResponse;
	private HttpServletRequest servletRequest;
	private String message;

	public CartManager() {

	}

	public CartManager(int userid, int productId, int quantity,
			ArrayList<CartModel> products) {
		super();
		this.userid = userid;
		this.productId = productId;
		this.quantity = quantity;
		this.products = products;
	}

	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the products
	 */
	public ArrayList<CartModel> getProducts() {
		return products;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(ArrayList<CartModel> products) {
		this.products = products;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		// TODO Auto-generated method stub
		this.servletRequest = servletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse servletResponse) {
		// TODO Auto-generated method stub
		this.servletResponse = servletResponse;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String addToCart() {
		User user = (User) session.get("user");
		if (user != null) {
			try {
				DBHandlerForCart.addToCart(
						Integer.parseInt(user.getUserId().trim()), productId,
						quantity);
			} catch (Exception e) {
				System.out.println("unable to add product to cart..!!!");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {

				String content = null;
				boolean cookieFound = false;
				for (Cookie c : servletRequest.getCookies()) {
					if (c.getName().equals("cart")) {
						content = c.getValue();
						CartCookie cookie = new CartCookie();
						JSONPopulator pop = new JSONPopulator();
						Map<?, ?> map = (Map<?, ?>) JSONUtil
								.deserialize(content);
						pop.populateObject(cookie, map);

						if (!cookie.getProductList().contains(
								new CartProduct(productId, quantity))) {
							cookie.getProductList().add(
									new CartProduct(productId, quantity));
							content = JSONUtil.serialize(cookie);
							c.setValue(content);
							c.setMaxAge(60 * 60 * 24 * 2);

							servletResponse.addCookie(c);

						}
						cookieFound = true;
						break;
					}
				}
				if (cookieFound == false) {
					CartCookie cookie = new CartCookie();
					cookie.getProductList().add(
							new CartProduct(productId, quantity));
					content = JSONUtil.serialize(cookie);
					Cookie c = new Cookie("cart", content);
					c.setMaxAge(60 * 60 * 24 * 2);
					servletResponse.addCookie(c);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "success";

	}

	public String getCartProducts() {
		User user = (User) session.get("user");

		if (user != null)// user logged in.. fetch cart from db
		{
			try {
				products = DBHandlerForCart.getProducts(Integer.parseInt(user
						.getUserId().trim()));
				for (CartModel c : products) {
					System.out.println(c.getImage());
				}
				System.out.println("");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to fetch datat from cart...!!!");
				e.printStackTrace();
			}

		} else {// user not logged in fetch from cookie

			try {

				String content = null;
				boolean cookieFound = false;
				for (Cookie c : servletRequest.getCookies()) {
					if (c.getName().equals("cart")) {
						content = c.getValue();
						CartCookie cookie = new CartCookie();
						JSONPopulator pop = new JSONPopulator();
						Map<?, ?> map = (Map<?, ?>) JSONUtil
								.deserialize(content);
						pop.populateObject(cookie, map);

						products = DBHandlerForCart.getProductsFromCart(cookie
								.getProductList());
						cookieFound = true;
						break;
					}
				}
				if (cookieFound == false) {
					products = new ArrayList<CartModel>();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		count = products.size();
		return "success";
	}

	public String removeCartProduct() {

		User user = (User) session.get("user");
		if (user != null) {
			try {
				DBHandlerForCart.removeFromCart(
						Integer.parseInt(user.getUserId().trim()), productId);

				products = DBHandlerForCart.getProducts(Integer.parseInt(user
						.getUserId().trim()));
			} catch (Exception e) {
				System.out.println("unable to remove product to cart..!!!");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {

				String content = null;
				for (Cookie c : servletRequest.getCookies()) {
					if (c.getName().equals("cart")) {
						content = c.getValue();
						CartCookie cookie = new CartCookie();
						JSONPopulator pop = new JSONPopulator();
						Map<?, ?> map = (Map<?, ?>) JSONUtil
								.deserialize(content);
						pop.populateObject(cookie, map);
						cookie.getProductList().remove(
								new CartProduct(productId, 1));
						content = JSONUtil.serialize(cookie);
						c.setValue(content);
						c.setMaxAge(60 * 60 * 24 * 2);
						servletResponse.addCookie(c);
						products = DBHandlerForCart.getProductsFromCart(cookie
								.getProductList());
						break;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "success";

	}

	public String updateCartProductQuantity() {
		DBHandlerForAdmin admin = new DBHandlerForAdmin();
		User user = (User) session.get("user");
		int availableQuantity = 0;

		try {
			availableQuantity = admin.fetchProductAvailableQuantity(productId);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (user != null) {
			try {

				if (availableQuantity >= quantity) {
					DBHandlerForCart.updateToCart(
							Integer.parseInt(user.getUserId().trim()),
							productId, quantity);
					message = "Quantity Successfully updated";
				} else {
					message = "Unable to update. Available Quantity : "
							+ availableQuantity;
				}

			} catch (Exception e) {
				System.out.println("unable to update quantity..!!!");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				if (availableQuantity >= quantity) {
					String content = null;
					boolean cookieFound = false;
					for (Cookie c : servletRequest.getCookies()) {
						if (c.getName().equals("cart")) {
							content = c.getValue();
							CartCookie cookie = new CartCookie();
							JSONPopulator pop = new JSONPopulator();
							Map<?, ?> map = (Map<?, ?>) JSONUtil
									.deserialize(content);
							pop.populateObject(cookie, map);

							if (cookie.getProductList().contains(
									new CartProduct(productId, quantity))) {
								for (CartProduct p : cookie.getProductList()) {
									if (p.getProductId() == productId) {
										p.setQuantity(quantity);
									}
								}
								content = JSONUtil.serialize(cookie);
								c.setValue(content);
								c.setMaxAge(60 * 60 * 24 * 2);

								servletResponse.addCookie(c);

							}
							cookieFound = true;
							break;
						}
					}
					message = "Quantity Successfully updated";
				} else {
					message = "Unable to update. Available Quantity : "
							+ availableQuantity;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "success";
	}

}
