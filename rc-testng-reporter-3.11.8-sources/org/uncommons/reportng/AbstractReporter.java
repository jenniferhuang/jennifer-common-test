package org.uncommons.reportng;

import java.io.*;
import java.util.ResourceBundle;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IReporter;

/**
 * Convenient base class for the ReportNG reporters.  Provides common functionality.
 * @author Daniel Dyer
 */
public abstract class AbstractReporter implements IReporter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractReporter.class);
    protected static final String ENCODING = "UTF-8";

    protected static final String TEMPLATE_EXTENSION = ".vm";

    protected static final String META_KEY ="meta";
    protected static final ReportMetadata META = new ReportMetadata();
    protected static final String UTILS_KEY ="utils";
    protected static final ReportNGUtils UTILS = new ReportNGUtils();
    protected static final String MESSAGES_KEY ="messages";
    protected static final ResourceBundle MESSAGES = ResourceBundle.getBundle("org.uncommons.reportng.messages.reportng",
                                                                            META.getLocale());

    private final String classpathPrefix;


    /**
     * @param classpathPrefix Where in the classpath to load templates from.
     */
    protected AbstractReporter(String classpathPrefix)
    {
        this.classpathPrefix = classpathPrefix;
        Velocity.setProperty("resource.loader", "classpath");
        Velocity.setProperty("classpath.resource.loader.class",
                             "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        if (!META.shouldGenerateVelocityLog())
        {
            Velocity.setProperty("runtime.log.logsystem.class",
                                 "org.apache.velocity.runtime.log.NullLogSystem");
        }

        try
        {
            Velocity.init();
        }
        catch (Exception ex)
        {
            throw new ReportNGException("Failed to initialise Velocity.", ex);
        }
    }


    /**
     * Helper method that creates a Velocity context and initialises it
     * with a reference to the ReportNG utils, report metadata and localised messages.
     * @return An initialised Velocity context.
     */
    protected VelocityContext createContext()
    {
        VelocityContext context = new VelocityContext();
        context.put(META_KEY, META);
        context.put(UTILS_KEY, UTILS);
        context.put(MESSAGES_KEY, MESSAGES);
        return context;
    }


    /**
     * Generate the specified output file by merging the specified
     * Velocity template with the supplied context.
     */
    protected void generateFile(File file,
                                String templateName,
                                VelocityContext context) throws Exception
    {
        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
            Velocity.mergeTemplate(classpathPrefix + templateName,
                    ENCODING,
                    context,
                    writer);
            writer.flush();
        }
    }


    /**
     * Copy a single named resource from the classpath to the output directory.
     * @param outputDirectory The destination directory for the copied resource.
     * @param resourceName The filename of the resource.
     * @param targetFileName The name of the file created in {@literal outputDirectory}.
     * @throws IOException If the resource cannot be copied.
     */
    protected void copyClasspathResource(File outputDirectory,
                                         String resourceName,
                                         String targetFileName) throws IOException
    {
        String resourcePath = classpathPrefix + resourceName;
        InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
        copyStream(outputDirectory, resourceStream, targetFileName);
    }


    /**
     * Copy a single named file to the output directory.
     * @param outputDirectory The destination directory for the copied resource.
     * @param sourceFile The path of the file to copy.
     * @param targetFileName The name of the file created in {@literal outputDirectory}.
     * @throws IOException If the file cannot be copied.
     */
    protected void copyFile(File outputDirectory,
                            File sourceFile,
                            String targetFileName) throws IOException
    {
        InputStream fileStream = new FileInputStream(sourceFile);
        try
        {
            copyStream(outputDirectory, fileStream, targetFileName);
        }
        finally
        {
            fileStream.close();
        }
    }


    /**
     * Helper method to copy the contents of a stream to a file.
     * @param outputDirectory The directory in which the new file is created.
     * @param stream The stream to copy.
     * @param targetFileName The file to write the stream contents to.
     * @throws IOException If the stream cannot be copied.
     */
    protected void copyStream(File outputDirectory,
                              InputStream stream,
                              String targetFileName) throws IOException
    {
        File resourceFile = new File(outputDirectory, targetFileName);
        BufferedReader reader = null;
        Writer writer = null;
        try
        {
            reader = new BufferedReader(new InputStreamReader(stream, ENCODING));
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resourceFile), ENCODING));

            String line = reader.readLine();
            while (line != null)
            {
                writer.write(line);
                writer.write('\n');
                line = reader.readLine();
            }
            writer.flush();
        }
        finally
        {
            if (reader != null)
            {
                reader.close();
            }
            if (writer != null)
            {
                writer.close();
            }
        }
    }

    /**
     * Helper method to copy the contents of a stream to a file.
     * @param outputDirectory The directory in which the new file is created.
     * @param targetFileName The file to write the stream contents to.
     * @throws IOException If the stream cannot be copied.
     */
    protected void copyImage(File outputDirectory,
                             String resourceName,
                             String targetFileName) throws IOException
    {
        String resourcePath = classpathPrefix + resourceName;
        InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
        File dst = new File(outputDirectory, targetFileName);
        OutputStream out = null;
        try
        {
            out = new FileOutputStream(dst);

            // Transfer bytes from in to out
            byte[] buf = new byte[1024];
            int len;
            while ((len = resourceStream.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            resourceStream.close();
        } catch (Exception ex ){
            LOGGER.debug(ex.getMessage(), ex);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * Deletes any empty directories under the output directory.  These
     * directories are created by TestNG for its own reports regardless
     * of whether those reports are generated.  If you are using the
     * default TestNG reports as well as ReportNG, these directories will
     * not be empty and will be retained.  Otherwise they will be removed.
     * @param outputDirectory The directory to search for empty directories.
     */
    protected void removeEmptyDirectories(File outputDirectory)
    {
        if (outputDirectory.exists())
        {
            for (File file : outputDirectory.listFiles(new EmptyDirectoryFilter()))
            {
                file.delete();
            }
        }
    }


    private static final class EmptyDirectoryFilter implements FileFilter
    {
        public boolean accept(File file)
        {
            return file.isDirectory() && file.listFiles().length == 0;
        }
    }
}