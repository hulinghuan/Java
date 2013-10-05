
public class People 
{
	private String m_name;			//People 名称
	private String m_gender;		//People 性别
	private int m_age;				//People 年龄
	private int m_id;				//People的统一ID
	
	/**
	 * people初始化
	 */
	public People()
	{
		m_name=null;
		m_gender=null;
		m_age=0;
		m_id=0;
	}
	
	/**
	 * 
	 * @return People名称
	 */
	public String getName()
	{
		return m_name;
	}
	/**
	 * 
	 * @return People性别
	 */
	public String getGender()
	{
		return m_gender;
	}
	/**
	 * 
	 * @return People年龄
	 */
	public int getAge()
	{
		return m_age;
	}
	/**
	 * 
	 * @return PeopleID
	 */
	public int getId()
	{
		return m_id;
	}
	/**
	 * 对People ID赋值
	 * @param mid
	 */
	public void SetId(int mid)
	{
		m_id=mid;
	}
	
	/**
	 * 对People Name赋值
	 * @param name
	 */
	public void SetName(String name)
	{
		m_name=name;
	}
	
	/**
	 * 对People 性别赋值
	 * @param gender
	 */
	public void SetGender(String gender)
	{
		m_gender=gender;
	}
	/**
	 * 对People年龄赋值
	 * @param age
	 */
	public void SetAge(int age)
	{
		m_age=age;
	}
	

}
