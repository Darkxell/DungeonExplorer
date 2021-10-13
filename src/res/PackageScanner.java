package res;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PackageScanner {

	public static List<String> getResourceFiles(String path) {
		try {
			List<String> filenames = new ArrayList<>();
			URI uri = PackageScanner.class.getResource(path).toURI();
			try (FileSystem fileSystem = (uri.getScheme().equals("jar")
					? FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap())
					: null)) {
				Path myPath = Paths.get(uri);
				Files.walkFileTree(myPath, new SimpleFileVisitor<Path>() {
					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						filenames.add(file.getFileName().toString());
						return FileVisitResult.CONTINUE;
					}
				});
			}
			return filenames;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public static InputStream getResourceAsStream(String resource) {
		final InputStream in = getContextClassLoader().getResourceAsStream(resource);
		return in == null ? PackageScanner.class.getResourceAsStream(resource) : in;
	}

	public static ClassLoader getContextClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

}
