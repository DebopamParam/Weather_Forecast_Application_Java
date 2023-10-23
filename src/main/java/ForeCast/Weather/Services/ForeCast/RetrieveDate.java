package ForeCast.Weather.Services.ForeCast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RetrieveDate {
    public String getDate(Long dt)
    {
        Long unixTimestamp = dt;
        Date date = new Date(unixTimestamp * 1000L); // Convert to milliseconds
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }
}
