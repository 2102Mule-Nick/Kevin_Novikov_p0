package bok.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import bok.pojo.Account;

public class UserDaoKryo implements UserDao {

	public Kryo kryo = new Kryo();

	private Logger log = Logger.getRootLogger();

	public static final String FOLDER_NAME = "clients\\";

	public static final String FILE_EXTENSION = ".dat";

	@Override
	public void generateAccount(Account account) {

		kryo.register(Account.class);
		File dir = new File(FOLDER_NAME);
		if (!dir.exists()) {
			dir.mkdir();
		}

		String filename = FOLDER_NAME + account.getUsername() + FILE_EXTENSION;
		File file = new File(filename);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch(IOException ioe) {
				log.error("Invalid directory...");
			}
		}
		log.info("Beginning Registration...");

		try (FileOutputStream outputStream = new FileOutputStream(
				FOLDER_NAME + account.getUsername() + FILE_EXTENSION)) {
			Output output = new Output(outputStream);
			kryo.writeObject(output, account);
			output.close();
		} catch (FileNotFoundException e) {
			log.error("File doesn't exist...", e);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		log.info("Registration complete!");
	}

	@Override
	public Account getAccountByUsername(String username) {

		try (FileInputStream inputStream = new FileInputStream(FOLDER_NAME + username + FILE_EXTENSION)) {
			Input input = new Input(inputStream);
			Account account = kryo.readObject(input, Account.class);
			input.close();
			return account;

		} catch (FileNotFoundException e) {
			log.error("File does not exists...");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Account> getAllClients() {
		List<Account> allClients = new ArrayList<Account>();
		File dir = new File(FOLDER_NAME);
		if (dir.isDirectory()) {
			File[] users = dir.listFiles();
			for (File f : users) {
				try (FileInputStream inputStream = new FileInputStream(f)) {
					Input input = new Input(inputStream);
					allClients.add(kryo.readObject(input, Account.class));
					input.close();
				} catch (FileNotFoundException e) {
					log.error("File does not exists...");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return allClients;
		}
		return null;
	}

	@Override
	public void updateAccount(Account account) {
		File f = new File(FOLDER_NAME + account.getUsername() + FILE_EXTENSION);
		f.delete();

		log.info("Updating account...");

		try (FileOutputStream outputStream = new FileOutputStream(
				FOLDER_NAME + account.getUsername() + FILE_EXTENSION)) {
			Output out = new Output(outputStream);
			kryo.writeObject(out, account);
			out.close();
		} catch (FileNotFoundException e) {
			log.error("File doesn't exist.", e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void closeAccount(Account account) {
		File f = new File(FOLDER_NAME + account.getUsername() + FILE_EXTENSION);
		f.delete();
	}
}
