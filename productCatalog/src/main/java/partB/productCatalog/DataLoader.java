package partB.productCatalog;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CommandLineRunner runs after the app starts. We insert some demo data.
 */
@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(ProductRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                Product p1 = new Product();
                p1.setName("Dog Multivitamin");
                p1.setDescription("Daily multivitamin chews for dogs. 60-count.");
                p1.setPrice(24.99);

                Product p2 = new Product();
                p2.setName("Cat Hairball Relief Gel");
                p2.setDescription("Helps reduce hairballs for adult cats.");
                p2.setPrice(14.50);

                Product p3 = new Product();
                p3.setName("Puppy Sensitive Stomach Kibble");
                p3.setDescription("Gentle formula for puppies with sensitive digestion.");
                p3.setPrice(39.99);

                repo.save(p1);
                repo.save(p2);
                repo.save(p3);
            }
        };
    }
}
