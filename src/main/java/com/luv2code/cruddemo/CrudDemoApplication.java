package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner (AppDAO appDAO)
	{
		return runner -> {
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);

			//createInstructorWithCourses(appDAO);
			
			//findInstructorWithCourses(appDAO);

			//findCoursesForInstructor(appDAO);

			//findInstructorWithCoursesJoinFetch(appDAO);

			//updateInstructor(appDAO);

			updateCourse(appDAO);
		};
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 10;

		System.out.println("Finding Course id : "+theId);
		Course tempCourse = appDAO.findCourseById(theId);
	}

	private void updateInstructor(AppDAO appDAO) {

		int theId = 1;

		// find the instructor
		System.out.println("Finding instructor id : " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		// update the instructor
		System.out.println("Updating instructor id : "+theId);
		tempInstructor.setLastName("Developer");

		appDAO.update(tempInstructor);

		System.out.println("Done !!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 1;

		// find the instructor
		System.out.println("Finding instructor id : "+theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("the associated courses : " + tempInstructor.getCourses());

		System.out.println("Done !!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id : "+ theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor : " + tempInstructor);
		//System.out.println("the associated courses : " + tempInstructor.getCourses());

		// find courses for the instructor
		System.out.println("finding Courses for instructor id : "+theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		//associate the courses
		tempInstructor.setCourses(courses);

		System.out.println("the associated courses : "+tempInstructor.getCourses());

		System.out.println("Done !!");

	}

	private void findInstructorWithCourses(AppDAO appDAO) {

		int theId = 1;

		System.out.println("Finding instructor id : "+ theId);

		// find the instructor

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor : "+ tempInstructor);

		// find courses for the instructor
		//System.out.println("Finding courses for instructor id: "+theId);
		//List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		// associate the objects
		//tempInstructor.setCourses(courses);

		//System.out.println("the associated courses : "+ tempInstructor.getCourses());


		System.out.println("Done");

	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		//create the instructor
		Instructor tempInstructor =
				new Instructor("Vimit","Moon","mvimit@gmail.com");
		// create the instructor details
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("https://www.youtube.com/@vimit","Coding");

		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		// saving the instructor will automatically save the
		// instructor details
		//
		//System.out.println("saving the instructor : "+tempInstructor);
		//appDAO.save(tempInstructor);

		//System.out.println("Done!");
		// Create some courses
		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The Pinball Masterclass");

		// add those courses
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// save the instructor
		//
		// NOTE = this will be saving the courses as well
		// because of CascadeType.PERSIST
		//
		System.out.println("Saving the instructor"+tempInstructor);
		System.out.println("The courses: "+tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting the instructor id :"+theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done..!");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Finding the instructor id : "+theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor : "+tempInstructor);

		System.out.println("the associated instructorDetails only :"+tempInstructor.getInstructorDetail());
	}
/*
	private void createInstructor(AppDAO appDAO) {
		//create the instructor		 tempInstructor =
				new Instructor("vimit","moon","vimitmoon@gmail.com");
		// create the instructor details
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("https://www.youtube.com/@vimitmoon5760","Singing");
*/

		private void createInstructor(AppDAO appDAO) {
			//create the instructor
			Instructor tempInstructor =
					new Instructor("Chad","Derby","luv2code@mail.com");
			// create the instructor details
			InstructorDetail tempInstructorDetail =
					new InstructorDetail("https://www.youtube.com/@chad","Coding");

			//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		// saving the instructor will automatically save the
		// instructor details
		//
		System.out.println("saving the instructor : "+tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}
}
