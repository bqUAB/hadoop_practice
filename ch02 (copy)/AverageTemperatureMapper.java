package org.myorg;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AverageTemperatureMapper
  extends Mapper<LongWritable, Text, Text, TemperatureAveragingPair> {
  //extends Mapper<LongWritable, Text, Text, IntWritable> {

  private Text outText = new Text();
  private TemperatureAveragingPair pair = new TemperatureAveragingPair();
  private static final int MISSING = 99999;

  @Override
  protected void map(LongWritable key, Text value, Context context)
    throws IOException, InterruptedException {
    String line = value.toString();
    String year = line.substring(15, 19);
/*    int airTemperature;
    if (line.charAt(87) == '+') { // parseInt doesn't like leading plus signs
      airTemperature = Integer.parseInt(line.substring(88, 93));
    } else {
      airTemperature = Integer.parseInt(line.substring(87, 93));
    }
    String quality = line.substring(92, 93);
    if (airTemperature != MISSING && quality.matches("[01459]")) {
      context.write(new Text(year), new IntWritable(airTemperature));
    }*/

      int tempStartPosition = 87;

      if (line.charAt(tempStartPosition) == '+') {
          tempStartPosition += 1;
      }

      int temp = Integer.parseInt(line.substring(tempStartPosition, 93));

      if (temp != MISSING) {
          outText.set(year);
          pair.set(temp, 1);
          context.write(outText, pair);
      }
    }
}
