//  [ vo 패키지(DTO) ]
// 개 상품 하나의 정보를 저장하는 클래스
package vo;

public class Dog {
	private int id; // 상품 아이디
	
	private String kind; // 개 품종
	private String country; // 원산지
	private int price; // 가격
	
	private int height; // 평균 개 신장
	private int weight; // 평균 개 체중
	private String content; // 개 설명
	
	private String image; // 개 이미지
	
	private int readcount; // 조회수
	
	// 매개변수가 있는 생성자를 만들었기 때문에 반드시 매개변수가 없는 생성자를 수동으로 생성해줘야함
	public Dog() { }
	
	public Dog(int id, String kind, String country, int price, int height, int weight, String content, String image,
			int readcount) {
		super();
		this.id = id;
		this.kind = kind;
		this.country = country;
		this.price = price;
		this.height = height;
		this.weight = weight;
		this.content = content;
		this.image = image;
		this.readcount = readcount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	
	
	
	
	
}
