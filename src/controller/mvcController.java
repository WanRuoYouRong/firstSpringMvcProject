package controller;




import java.io.Serializable;
import java.util.*;

import javax.annotation.Resource;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.PersonDao;
import model.Person;
import org.apache.activemq.ActiveMQConnectionFactory;



@Controller
@RequestMapping("/mvc")
public class mvcController {
	
	@Resource
    PersonDao ps;
	public static List<Person> getperson;
	

    @RequestMapping("/hello")
    public String hello(){        
        return "hello";
    }
    
    @RequestMapping(value = "/person/all")
    public String findAll(Model model){     
        PersonDao person=new PersonDao();
    	List<Person> personList;
    	personList=person.getPersonarraylist();
    	model.addAttribute("personlist", personList);          
        return "/JpersonList";               
    }
    
    @RequestMapping(value = "/person/add")
    public String addUser(){
    	return "JpersonCreate";
    }
    
    @RequestMapping("/person/addPersonList")
    public String addPersonList(String name,int age,int salary){
        Person pr=new Person();// 调用 Service 层方法，插入数据
        pr.setName(name);
        pr.setAge(age);
        pr.setSalary(salary);
        ps.insert(pr);
        return "redirect:all";        // 转向人员列表 action
    }
    
    @RequestMapping(value = "/person/update")
    public String updateUser(){
    	return "JpersonUpdate";
    }
    
    @RequestMapping("/person/updatePersonList")
    public String updatePersonList(int id,String name,int age,int salary){
        Person pr=new Person();// 调用 Service 层方法，插入数据
        pr.setId(id);
        pr.setName(name);
        pr.setAge(age);
        pr.setSalary(salary);
        ps.update(pr);;
        return "redirect:all";        // 转向人员列表 action
    }
    
    @RequestMapping(value = "/person/send")
    public String creUser()throws JMSException{
    	PersonDao person=new PersonDao();
    	List<Person> personList;
    	personList=person.getPersonarraylist();//这里的list已经获得了数据库所有的数据
    	
    	
    	ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");  
        Connection connection = factory.createConnection();  
        connection.start();  
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        Topic topic = session.createTopic("myTopic.messages");  
        MessageProducer producer = session.createProducer(topic);  
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        
        
//        TextMessage message = session.createTextMessage();  
//        message.setText("message_" + System.currentTimeMillis());  
//        producer.send(message);  
//        System.out.println("Sent message: " + message.getText());
        
        ObjectMessage objectMessage = session.createObjectMessage();  
        objectMessage.setObject((Serializable) personList);  
        producer.send(objectMessage); 
        System.out.println("发送了一个object对象");
        session.close();  
        connection.stop();  
        connection.close();

    	
    	return "JpersonSend";
    }
    
    @RequestMapping(value = "/person/get")
    public String getUser(Model model)throws JMSException{
    	ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");  
        Connection connection = factory.createConnection();  
        connection.start();   
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
        Topic topic = session.createTopic("myTopic.messages");  
  
        MessageConsumer consumer = session.createConsumer(topic);
        
        
        consumer.setMessageListener(new MessageListener() {  
            public void onMessage(Message message) {  
            	ObjectMessage tm = (ObjectMessage) message;  
            	
            	try {
            		getperson=(List<Person>) ((ObjectMessage) tm).getObject();
					
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            }  
        });  
//      session.close();  
//      connection.stop();  
//      connection.close();  
    	
    	

        model.addAttribute("personlist", getperson);
    	return "JpersonGet";
    }
    
    
}
