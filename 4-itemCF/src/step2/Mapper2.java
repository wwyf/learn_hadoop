import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileSystem;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.InterruptedException;
import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.text.DecimalFormat;
public class Mapper2 extends Mapper<LongWritable, Text, Text, Text> {
    private Text outKey = new Text();
    private Text outValue = new Text();
    private List<String> cacheList = new ArrayList<String>();
    private DecimalFormat df = new DecimalFormat("0.00");
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
        /*
        FileReader fr = new FileReader("matrix2");
        BufferedReader bf = new BufferedReader(fr);
        
        String line = null;
        while ((line = bf.readLine()) != null) {
            cacheList.add(line);
        }
        fr.close();
        bf.close();
        */
        Configuration conf = context.getConfiguration();
        FileSystem fs = FileSystem.get(conf);
        FSDataInputStream in = fs.open(new Path("step1_output_matrix/part-r-00000"));
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while ((line = br.readLine()) != null) {
            cacheList.add(line);
        }
        in.close();
        br.close();
    }

    /**
     * @param key: row
     * @param value: row tab col_value, ...,
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) 
        throws IOException, InterruptedException {

        String row_matrix1 = value.toString().split("\t")[0];
        String[] column_value_array_matrix1 = value.toString().split("\t")[1].split(",");

        // calculate E-distance
        double denominator1 = 0;
        for (String column_value : column_value_array_matrix1) {
            String score = column_value.split("_")[1];//TODO: change here from 0 to 1
            denominator1 += Double.valueOf(score) * Double.valueOf(score);
        }
        denominator1 = Math.sqrt(denominator1);

        for (String line : cacheList) {
            String row_matrix2 = line.split("\t")[0];
            String[] column_value_array_matrix2 = line.split("\t")[1].split(",");
            
            double denominator2 = 0;
            for (String row_value : column_value_array_matrix2) {
                String score = row_value.split("_")[1]; //TODO: change here from 0 t 1
                denominator2 += Double.valueOf(score) * Double.valueOf(score);
            }
            denominator2 = Math.sqrt(denominator2);

            double numerator = 0;
            for (String cv : column_value_array_matrix1) {
                String column_matrix1 = cv.split("_")[0];
                String value_matrix1 = cv.split("_")[1];

                for (String cvm2 : column_value_array_matrix2) {
                    if (cvm2.startsWith(column_matrix1 + "_")) {
                        String value_matrix2 = cvm2.split("_")[1];
                        numerator += Double.valueOf(value_matrix1) * Double.valueOf(value_matrix2);
                    }
                }
            }

            double cos = numerator / (denominator2 * denominator1);
            if (cos == 0) {
                continue;
            }
            
            outKey.set(row_matrix1);
            outValue.set(row_matrix2 + "_" + df.format(cos));
            context.write(outKey, outValue);
        }
    }
}

