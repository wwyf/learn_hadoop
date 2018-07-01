import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.io.Text;
import java.io.IOException;
import java.lang.InterruptedException;
import java.lang.ClassNotFoundException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.fs.FileSystem;

public class Mapred {
    private static String inPath = "step1_output_matrix/"; //TODO: maybe bug
    private static String outPath = "step2_output_matrix";
    private static String hdfs = "hdfs://lxr-ubuntu-server:8020"; //TODO: maybe need chagne

    public int run() {
        try {
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", hdfs);
            Job job = Job.getInstance(conf, "step2");

            job.setJarByClass(Mapred.class);
            job.setMapperClass(Mapper2.class);
            job.setReducerClass(Reducer2.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            FileSystem fs = FileSystem.get(conf);
            Path path = new Path(inPath);
            if (fs.exists(path)) {
                FileInputFormat.addInputPath(job, path);
            } else {
                System.out.println("error! not exist");
            }
            Path outputPath = new Path(outPath);
            fs.delete(outputPath ,true);

            FileOutputFormat.setOutputPath(job, outputPath);
            return job.waitForCompletion(true) ? 1 : -1;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;

    }
    public static void main(String[] args) {
        int result = -1;
        result = new Mapred().run();
        if (result == 1) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
    }
}
