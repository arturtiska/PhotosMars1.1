package com.example.android.marsphotos.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.databinding.GridViewItemBinding
import com.example.android.marsphotos.network.MarsPhotos

/**
 * Esta classe implementa um [RecyclerView] [ListAdapter] que usa Data Binding para apresentar [List]
 * dados, incluindo diferenças de computação entre listas.
 */
class PhotoGridAdapter :
    ListAdapter<MarsPhotos, PhotoGridAdapter.MarsPhotosViewHolder>(DiffCallBack) {

    /**
     * O construtor MarsPhotosViewHolder pega a variável de ligação do associado
     * GridViewItem, que dá acesso a todas as informações [MarsPhoto].
     */

    class MarsPhotosViewHolder(
        private var binding: GridViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(marsPhoto: MarsPhotos) {
            binding.photo = marsPhoto
            // Isso é importante porque força a execução imediata da vinculação de dados,
            // que permite que o RecyclerView faça as medições corretas do tamanho da visualização
            binding.executePendingBindings()
        }
    }

    //. O método onCreateViewHolder() precisa retornar um novo MarsPhotoViewHolder,
    // criado inflando a GridViewItemBinding e usando o LayoutInflater do contexto ViewGroup pai.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotosViewHolder {
        return MarsPhotosViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    // Neste método, você chamará getItem() para receber o objeto MarsPhoto associado à posição atual
    // do RecyclerView e, em seguida, transmitirá essa propriedade para o método bind() no
    // MarsPhotoViewHolder.
    override fun onBindViewHolder(holder: MarsPhotosViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }

    companion object DiffCallBack :  DiffUtil.ItemCallback<MarsPhotos>() {

        //Esse método é chamado pelo DiffUtil para decidir se dois objetos representam o mesmo item.
        // O DiffUtil usa este método para descobrir se o novo objeto MarsPhoto é igual ao objeto
        // MarsPhoto antigo. O ID de cada item (os objetos MarsPhoto) é único.
        // Compare os IDs de oldItem e newItem e retorne o resultado
        override fun areItemsTheSame(oldItem: MarsPhotos, newItem: MarsPhotos): Boolean {
            return oldItem.id == newItem.id
        }
        //Este método é chamado pelo DiffUtil ao verificar se dois itens têm os mesmos dados.
        // Os dados importantes do objeto MarsPhoto são o URL da imagem. Compare os URLs do oldItem
        // e do newItem e retorne o resultado.
        override fun areContentsTheSame(oldItem: MarsPhotos, newItem: MarsPhotos): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }
}

