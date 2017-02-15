package org.myorg;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxTemperatureReducer
  extends Reducer<Text, IntWritable, Text, IntWritable> {
  
  @Override
  public void reduce(Text key, Iterable<IntWritable> values,
      Context context)
      throws IOException, InterruptedException {

    //int maxValue = Integer.MIN_VALUE;
    int cont = 0;
    int sum = 0; //Integer.MIN_VALUE;;
    for (IntWritable value : values) {
      //maxValue = Math.max(maxValue, value.get());
      sum += value.get();
      cont += 1;
    }
    int avgValue = sum / cont;
    //int avgValue = sum;

    //context.write(key, new IntWritable(maxValue));
    context.write(key, new IntWritable(avgValue));
  }
}
