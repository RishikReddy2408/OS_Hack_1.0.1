package com.google.android.android.internal.measurement;

final class zzxx
{
  static String toString(zzud paramZzud)
  {
    paramZzud = new zzxy(paramZzud);
    StringBuilder localStringBuilder = new StringBuilder(paramZzud.size());
    int i = 0;
    while (i < paramZzud.size())
    {
      int j = paramZzud.zzal(i);
      if (j != 34)
      {
        if (j != 39)
        {
          if (j != 92) {
            switch (j)
            {
            default: 
              if ((j >= 32) && (j <= 126))
              {
                localStringBuilder.append((char)j);
              }
              else
              {
                localStringBuilder.append('\\');
                localStringBuilder.append((char)((j >>> 6 & 0x3) + 48));
                localStringBuilder.append((char)((j >>> 3 & 0x7) + 48));
                localStringBuilder.append((char)((j & 0x7) + 48));
              }
              break;
            case 13: 
              localStringBuilder.append("\\r");
              break;
            case 12: 
              localStringBuilder.append("\\f");
              break;
            case 11: 
              localStringBuilder.append("\\v");
              break;
            case 10: 
              localStringBuilder.append("\\n");
              break;
            case 9: 
              localStringBuilder.append("\\t");
              break;
            case 8: 
              localStringBuilder.append("\\b");
              break;
            case 7: 
              localStringBuilder.append("\\a");
              break;
            }
          } else {
            localStringBuilder.append("\\\\");
          }
        }
        else {
          localStringBuilder.append("\\'");
        }
      }
      else {
        localStringBuilder.append("\\\"");
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
}
