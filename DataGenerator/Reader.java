import java.sql.Statement;

public interface Reader {
	public abstract void setup(Statement stmt);
}
