
public class People 
{
	private String m_name;			//People ����
	private String m_gender;		//People �Ա�
	private int m_age;				//People ����
	private int m_id;				//People��ͳһID
	
	/**
	 * people��ʼ��
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
	 * @return People����
	 */
	public String getName()
	{
		return m_name;
	}
	/**
	 * 
	 * @return People�Ա�
	 */
	public String getGender()
	{
		return m_gender;
	}
	/**
	 * 
	 * @return People����
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
	 * ��People ID��ֵ
	 * @param mid
	 */
	public void SetId(int mid)
	{
		m_id=mid;
	}
	
	/**
	 * ��People Name��ֵ
	 * @param name
	 */
	public void SetName(String name)
	{
		m_name=name;
	}
	
	/**
	 * ��People �Ա�ֵ
	 * @param gender
	 */
	public void SetGender(String gender)
	{
		m_gender=gender;
	}
	/**
	 * ��People���丳ֵ
	 * @param age
	 */
	public void SetAge(int age)
	{
		m_age=age;
	}
	

}
