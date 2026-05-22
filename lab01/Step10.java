package lab01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Step10 {
    // Step 10: advanced OOP design with generics and interfaces

    interface Identifiable {
        int getId();
    }

    interface Repository<T extends Identifiable> {
        void save(T item);

        T findById(int id);

        List<T> findAll();
    }

    static class MyRepository<T extends Identifiable> implements Repository<T> {
        private final List<T> items = new ArrayList<>();

        @Override
        public void save(T item) {
            items.add(item);
        }

        @Override
        public T findById(int id) {
            for (T item : items) {
                if (item.getId() == id) {
                    return item;
                }
            }
            return null;
        }

        @Override
        public List<T> findAll() {
            return new ArrayList<>(items);
        }
    }

    static abstract class User implements Identifiable {
        private final int id;
        private final String name;

        User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int getId() {
            return id;
        }

        String getName() {
            return name;
        }

        abstract String getRole();

        @Override
        public String toString() {
            return getRole() + "{" + "id=" + id + ", name='" + name + "'}";
        }
    }

    static class Student extends User {
        Student(int id, String name) {
            super(id, name);
        }

        @Override
        String getRole() {
            return "Student";
        }
    }

    static class Teacher extends User {
        Teacher(int id, String name) {
            super(id, name);
        }

        @Override
        String getRole() {
            return "Teacher";
        }
    }

    static class UserService {
        private final Repository<User> repository;

        UserService(Repository<User> repository) {
            this.repository = repository;
        }

        void register(User user) {
            repository.save(user);
        }

        void printUsersSortedByName() {
            List<User> users = repository.findAll();
            users.sort(Comparator.comparing(User::getName));
            users.forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
        Repository<User> repo = new MyRepository<>();
        UserService service = new UserService(repo);

        service.register(new Student(1, "Ava"));
        service.register(new Teacher(2, "Dr. Smith"));
        service.register(new Student(3, "Noah"));

        service.printUsersSortedByName();

        User found = repo.findById(2);
        System.out.println("Found: " + found);
    }
}
