import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
import java.lang.InterruptedException;
import java.util.HashMap;
import java.util.Map;


public class Reducer1 extends Reducer<Text, Text, Text, Text> {
    private Text outKey = new Text();
    private Text outValue = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) 
        throws IOException, InterruptedException {
        String itemID = key.toString();
        // key: userID, value: score
        Map<String, Double> map = new HashMap<String, Double>();

        for (Text value : values) {
            String userID = value.toString().split("_")[0];
            String score = value.toString().split("_")[1];

            if (map.get(userID) == null) {
                map.put(userID, Double.valueOf(score));
            } else {
                Double preScore = map.get(userID);
                map.put(userID, preScore + Double.valueOf(score));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Double> entry: map.entrySet()) {
            String userID = entry.getKey();
            String score = String.valueOf(entry.getValue());
            sb.append(userID + "_" + score + ",");
        }
        String line = null;
        if (sb.toString().endsWith(",")) {
            line = sb.substring(0, sb.length() -1);
        }
        outKey.set(itemID);
        outValue.set(line);
        context.write(outKey, outValue);
    }
}
