package pet.cc.minucurso_android.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import pet.cc.minucurso_android.Fragment.ConversasFragment;
import pet.cc.minucurso_android.Fragment.ChamadasFragment;
import pet.cc.minucurso_android.Fragment.StatusFragment;

public class TabAdapter extends FragmentStatePagerAdapter {
    //O FragmentStatePagerAdapter vai nos obrigar a sobrescrever dois métodos,
    //@getItem( ) <- que vai retornar uma instancia de um Fragment a ser carregado no viewPager
    //@getCount( ) <- retorna a quantidade de tabs
    private int countTabs;
    public TabAdapter(FragmentManager fm, int countTabs) {
        super(fm);
        this.countTabs = countTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new ConversasFragment();
            case 1:
                return new StatusFragment();
            case 2:
                return new ChamadasFragment();
            default:
                return new ConversasFragment();
        }
    }

    @Override
    public int getCount() {
        return countTabs;
    }
}
