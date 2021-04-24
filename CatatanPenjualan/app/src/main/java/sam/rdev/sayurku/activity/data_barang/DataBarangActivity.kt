package sam.rdev.sayurku.activity.data_barang

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import sam.rdev.sayurku.R
import sam.rdev.sayurku.activity.data_barang._add.AddBarangActivity
import sam.rdev.sayurku.activity.data_barang.adapter.DataBarangAdapter
import sam.rdev.sayurku.activity.data_barang.presenter.DataBarangPresenter
import sam.rdev.sayurku.activity.data_barang.presenter.DataBarangView
import sam.rdev.sayurku.base.BaseActivity
import sam.rdev.sayurku.model.Barang
import kotlinx.android.synthetic.main.activity_data_barang.*

class DataBarangActivity : BaseActivity(), DataBarangView {
    override fun onCreate(savedInstanceState: Bundle?) {
        cekSesi(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_barang)

        setActionButton()

        refreshBarang()
    }

    private fun setActionButton() {
        btAddDataBarang.onClick {
            startActivity<AddBarangActivity>(TAGS.USER to user)
        }
    }

    private fun refreshBarang() {
        DataBarangPresenter(this).getDataBarang(user)
    }


    override fun onSuccessDataBarang(data: List<Barang?>?) {
        rvDataBarang.adapter = DataBarangAdapter(data, object : DataBarangAdapter.OnMenuClicked{
            override fun click(menuItem: MenuItem, barang: Barang?) {
                when(menuItem.itemId) {
                    R.id.editBarang -> editBarang(barang)
                    R.id.hapusBarang -> hapusBarang(barang)
                }
            }
        })
    }

    override fun onErrorDataBarang(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun editBarang(barang: Barang?) {
        val intent = Intent(this, AddBarangActivity::class.java)
        intent.putExtra(TAGS.USER, user)
        intent.putExtra(TAGS.BARANG, barang)

        startActivityForResult(intent, 1)
    }

    private fun hapusBarang(barang: Barang?) {
        alert {
            title = "Konfirmasi"
            message = "Yakin ingin menghapus barang ${barang?.namaBarang}"

            positiveButton("Hapus") {
                DataBarangPresenter(this@DataBarangActivity).deleteBarang(user, barang)
            }
            negativeButton("Batal") {}
        }.show()
        refreshBarang()
    }

    override fun onResume() {
        super.onResume()
        refreshBarang()
    }


    override fun onSuccessDeleteBarang(msg: String?) {
        toast(msg ?: "").show()
        refreshBarang()
    }

    override fun onErrorDeleteBarang(msg: String?) {
        toast(msg ?: "data sudah digunakan").show()
    }
}
