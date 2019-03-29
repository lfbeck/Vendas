package br.edu.ifsul.vendas.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.edu.ifsul.vendas.R;
import br.edu.ifsul.vendas.model.Cliente;



public class ClientesAdapter extends ArrayAdapter<Cliente> {

    private Context context;
    private List<Cliente> clientes;

    public ClientesAdapter(@NonNull Context context, @NonNull List<Cliente> clientes) {
        super(context, 0, clientes);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Devolve o objeto do modelo
        Cliente cliente = getItem(position);

        //infla a view
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cliente_adapter, parent, false);
        }

        //mapeia os componentes da UI para vincular os dados do objeto de modelo
        TextView tvNome = convertView.findViewById(R.id.tvNomeClientesAdapter);
        TextView tvSobrenome = convertView.findViewById(R.id.tvSobrenomeClientesAdapter);
        TextView tvCPF = convertView.findViewById(R.id.tvCPFItemAdapter);
        ImageView imvFoto = convertView.findViewById(R.id.imvFotoClienteAdapter);

        //vincula os dados do objeto de modelo à view
        tvNome.setText(cliente.getNome());
        tvSobrenome.setText(cliente.getSobrenome());
        tvCPF.setText(cliente.getCpf());
        if(cliente.getUrl_foto() != null){
            //aqui vai vincular a foto do cliente vindo do firebase usando a biblioteca Picasso
            Picasso.with(context).load(cliente.getUrl_foto()).fit().into(imvFoto, new Callback() {
                @Override
                public void onSuccess() {
                    Log.d("sucesso", "carregou");

                }

                @Override
                public void onError() {
                    Log.d("erro", "sefud");

                }
            });
        }else{
            imvFoto.setImageResource(R.drawable.img_cliente_icon_524x524);
        }


        return convertView;
    }
}
