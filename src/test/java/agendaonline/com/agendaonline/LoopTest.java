package agendaonline.com.agendaonline;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoopTest {

	@Test
	public void loop() {
		for (int i = 3; i != 0; i--) {
			System.out.println(i + ":  " + i--);
		}
	}

}
