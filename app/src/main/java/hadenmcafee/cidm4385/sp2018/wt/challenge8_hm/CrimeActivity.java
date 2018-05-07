package hadenmcafee.cidm4385.sp2018.wt.challenge8_hm;

import android.support.v4.app.Fragment;

public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment()
    {
        return new CrimeFragment();
    }
}
