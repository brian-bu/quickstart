package net.brian.coding.java.utils.operationsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.InteractiveCallback;
import ch.ethz.ssh2.KnownHosts;
import ch.ethz.ssh2.ServerHostKeyVerifier;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public final class UnixCommandsUtil {
	
	private UnixCommandsUtil() {}

	private static String knownHostPath = "knownHost";

	private static KnownHosts database = new KnownHosts();

	private BufferedReader reader;

	public Result execute(String hostName, int port, String userName,
			String pass, String[] commands) {

		Result result = new Result();

		Connection con = new Connection(hostName, port);

		try {

			String[] hostKeyAlgos = database
					.getPreferredServerHostkeyAlgorithmOrder(hostName);

			if (null != hostKeyAlgos) {

				con.setServerHostKeyAlgorithms(hostKeyAlgos);
			}

			con.connect(new ServerHostKeyVerifier() {

				@Override
				public boolean verifyServerHostKey(String hostName, int port,
						String serverHostKeyAlgorithm, byte[] serverHostKey)
						throws Exception {

					int result = database.verifyHostkey(hostName,
							serverHostKeyAlgorithm, serverHostKey);

					switch (result) {

					case KnownHosts.HOSTKEY_IS_OK:

						return true;

					case KnownHosts.HOSTKEY_HAS_CHANGED:

						break;

					case KnownHosts.HOSTKEY_IS_NEW:

						break;

					default:
						throw new IllegalStateException();
					}

					String hashedHostName = KnownHosts
							.createHashedHostname(hostName);
					// hashedHostName = hostName;
					database.addHostkey(new String[] { hashedHostName },
							serverHostKeyAlgorithm, serverHostKey);

					KnownHosts.addHostkeyToFile(new File(knownHostPath),
							new String[] { hashedHostName },
							serverHostKeyAlgorithm, serverHostKey);

					return true;
				}

			});

		} catch (IOException e) {

			e.printStackTrace();
			result.setStatus(false);
			result.setErrMsg(e.getMessage());
			return result;
		}

		Session session = null;

		boolean isAuthenticated = false;
		try {

			final String pwd = pass;
			if (con.isAuthMethodAvailable(userName, "keyboard-interactive")) {

				isAuthenticated = con.authenticateWithKeyboardInteractive(
						userName, new InteractiveCallback() {

							@Override
							public String[] replyToChallenge(String name,
									String instruction, int numPrompts,
									String[] prompt, boolean[] echo)
									throws Exception {

								String[] result = new String[numPrompts];

								for (int i = 0; i < numPrompts; i++) {

									result[i] = pwd;
								}
								return result;
							}

						});
			} else if (con.isAuthMethodAvailable(userName, "publickey")) {

				isAuthenticated = con.authenticateWithPublicKey(userName,
						new char[0], pass);
			} else {

				isAuthenticated = con.authenticateWithPassword(userName, pass);

			}

			if (!isAuthenticated) {

				System.out.println(hostName + " Authentication failed.");
				result.setStatus(false);
				result.setErrMsg(hostName + " Authentication failed.");
				return result;
			}

			session = con.openSession();
			String command = "";
			for (int i = 0; i < commands.length; i++) {
				command = command + commands[i] + "\n";
			}
			System.out.println(command);
			session.execCommand(command);

			StringBuilder errsb = new StringBuilder();

			InputStream stdErr = new StreamGobbler(session.getStderr());
			// 因为此处声明reader会有警告信息：reader never closed，所以将reader转换成实例变量
			reader = new BufferedReader(new InputStreamReader(
					stdErr));

			while (true) {
				String line = reader.readLine();
				if (null == line) {
					break;
				}
				errsb.append(line + "\n");
			}
			result.setErrMsg(errsb.toString());

			StringBuilder successsb = new StringBuilder();

			InputStream stdOut = new StreamGobbler(session.getStdout());
			reader = new BufferedReader(new InputStreamReader(stdOut));

			while (true) {

				String line = reader.readLine();
				if (null == line) {

					break;
				}
				successsb.append(line + "\n");
			}

			result.setSuccessMsg(successsb.toString());

			if (errsb.length() <= 0) {

				result.setStatus(true);
			} else {

				result.setStatus(false);
			}

		} catch (IOException e) {

			e.printStackTrace();
			result.setStatus(false);
			result.setErrMsg(e.getMessage());
			return result;
		} finally {
			if (null != session) {
				session.close();
				session = null;
			}
			if (null != con) {
				con.close();
				con = null;
			}
		}

		return result;

	}

}
class Result {

	public void setStatus(boolean b) {
		
	}

	public void setErrMsg(String message) {
		
	}

	public void setSuccessMsg(String string) {
		
	}

}



