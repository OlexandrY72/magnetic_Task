package utils;

	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.util.Properties;
	import java.util.Set;

	public class ConfigurationReader {
		private static Properties configFile;

		static {
			try {
				String path = "src/test/resources/testdata/testData.properties";
				FileInputStream input = new FileInputStream(path);

				configFile = new Properties();
				configFile.load(input);

				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static String getProperty(String keyName) {
			return configFile.getProperty(keyName);
		}
		
		public static void setProperty(String newKey, String newValue) throws FileNotFoundException, IOException {
			configFile.store(new FileOutputStream("src/test/resources/testdata/testData.properties"), null);
			configFile.setProperty(newKey, newValue);
		}
		
		public Set<Object> getAllKeys(){
			Set<Object> keys = configFile.keySet();
			return keys;
		}
	}